package com.example.githubexplorerkotlin.data.local.converter

import androidx.room.TypeConverter
import com.example.githubexplorerkotlin.data.model.repo.License
import com.example.githubexplorerkotlin.data.model.repo.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    private val gson = Gson()

    @TypeConverter
    fun licenseToJson(license: License?) : String?{
        license.let {
           return gson.toJson(license)
        }
    }

    @TypeConverter
    fun jsonToLicense(json: String?): License?{
        json.let {
            return gson.fromJson(json, License::class.java)
        }
    }

    @TypeConverter
    fun ownerToJson(owner: Owner?) : String? {
        owner.let {
            return gson.toJson(owner)
        }
    }

    @TypeConverter
    fun jsonToOwner(json: String?) : Owner? {
        json.let {
            return gson.fromJson(json, Owner::class.java)
        }
    }

    @TypeConverter
    fun listToJson(list: List<String>?) : String? {
        list.let {
            return gson.toJson(list)
        }
    }

    @TypeConverter
    fun jsonToList(json: String?) : List<String>?{
        json.let {
            return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
        }
    }


}