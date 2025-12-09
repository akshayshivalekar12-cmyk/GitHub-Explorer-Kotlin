package com.example.githubexplorerkotlin.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean?,
    @Json(name = "items")
    val items: List<Item?>?,
    @Json(name = "total_count")
    val totalCount: Int?
)