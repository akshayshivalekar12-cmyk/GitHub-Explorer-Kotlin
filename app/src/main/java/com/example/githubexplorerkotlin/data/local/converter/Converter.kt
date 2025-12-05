package com.example.githubexplorerkotlin.data.local.converter

import androidx.room.TypeConverter
import com.example.githubexplorerkotlin.data.model.Item
import com.example.githubexplorerkotlin.data.model.License
import com.example.githubexplorerkotlin.data.model.Owner
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun fromItem(item: Item) : String? {
        return Gson().toJson(item)
    }

    @TypeConverter
    fun fromLicense(license: License) : String? {
        return Gson().toJson(license)
    }

    @TypeConverter
    fun fromOwner(owner: Owner) : String? {
        return Gson().toJson(owner)
    }
}