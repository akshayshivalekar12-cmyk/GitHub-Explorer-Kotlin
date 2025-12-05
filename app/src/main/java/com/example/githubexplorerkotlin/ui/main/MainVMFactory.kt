package com.example.githubexplorerkotlin.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubexplorerkotlin.data.repository.MainRepository

class MainVMFactory(private val repo: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repo) as T
        }

        throw IllegalArgumentException("Illegal ViewModel Class")
    }
}