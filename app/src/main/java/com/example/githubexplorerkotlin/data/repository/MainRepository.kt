package com.example.githubexplorerkotlin.data.repository

import com.example.githubexplorerkotlin.data.local.RepoDao
import com.example.githubexplorerkotlin.data.model.Item
import com.example.githubexplorerkotlin.data.remote.RetrofitInstance

class MainRepository(private val dao: RepoDao) {

    suspend fun searchRepos(query: String) : List<Item>{
        val result = RetrofitInstance.api.getRepos(query);
        dao.insertRepos(result.items)
        return result.items
    }

    suspend fun getCachedRepos(): List<Item>{
        return dao.getAllRepos()
    }
}