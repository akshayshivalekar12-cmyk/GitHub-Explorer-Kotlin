package com.example.githubexplorerkotlin.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    fun getDatabase(context: Context) : RepoDatabase {
       return Room.databaseBuilder(
           context.applicationContext,
            RepoDatabase::class.java,
            "repo_db")
           .fallbackToDestructiveMigration()
           .build()
    }
}