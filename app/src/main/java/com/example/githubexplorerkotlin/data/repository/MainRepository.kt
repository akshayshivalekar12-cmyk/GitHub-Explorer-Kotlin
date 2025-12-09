package com.example.githubexplorerkotlin.data.repository

import com.example.githubexplorerkotlin.data.local.RepoDao
import com.example.githubexplorerkotlin.data.model.repo.Item
import com.example.githubexplorerkotlin.data.remote.RetrofitInstance

class MainRepository(private val dao: RepoDao) {

    suspend fun searchRepos(query: String) : List<Item>{
        val result = RetrofitInstance.api.getRepos(query)
        val cleanList = result.items?.filterNotNull() ?: emptyList()
        dao.insertRepos(cleanList)
        return cleanList
    }

    suspend fun getCachedRepos(): List<Item>{
        return dao.getAllRepos()
    }

    suspend fun toggleFavourites(repo: Item){
        dao.updateFavouriteRepo(repo.id!!, !repo.isFavourite)
    }
}