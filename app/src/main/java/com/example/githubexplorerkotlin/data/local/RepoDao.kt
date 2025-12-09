package com.example.githubexplorerkotlin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.githubexplorerkotlin.data.model.Item

@Dao
interface RepoDao {

    @Insert
    suspend fun insertRepos(repos: List<Item>)

    @Query("SELECT * FROM repo_table")
    suspend fun getAllRepos() : List<Item>
}