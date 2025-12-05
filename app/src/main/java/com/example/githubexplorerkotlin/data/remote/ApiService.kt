package com.example.githubexplorerkotlin.data.remote

import com.example.githubexplorerkotlin.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    suspend fun getRepos(@Query("q") q: String) : Repo
}