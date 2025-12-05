package com.example.githubexplorerkotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubexplorerkotlin.data.local.converter.Converter
import com.example.githubexplorerkotlin.data.model.Item


@Database(entities = [Item::class], version = 1)
@TypeConverters(Converter::class)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDao() : RepoDao
}