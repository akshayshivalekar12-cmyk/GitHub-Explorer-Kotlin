package com.example.githubexplorerkotlin.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.githubexplorerkotlin.data.model.repo.Item

@Dao
interface RepoDao {

    @Insert
    suspend fun insertRepos(repos: List<Item>)

    @Query("SELECT * FROM repo_table")
    suspend fun getAllRepos() : List<Item>

    @Query("SELECT * FROM repo_table WHERE isFavourite = 1")
    suspend fun getFavouriteRepos() : List<Item>

    @Query("UPDATE repo_table SET isFavourite = :fav WHERE id = :id")
    suspend fun updateFavouriteRepo(id: Int, fav: Boolean)

}