package com.example.githubexplorerkotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubexplorerkotlin.data.model.Item
import com.example.githubexplorerkotlin.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MainRepository) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _repos = MutableLiveData<List<Item>>()
    val repos : LiveData<List<Item>> = _repos

    fun searchRepos(query: String){
        viewModelScope.launch {
            try {
                _loading.value = true
                val results = repo.searchRepos(query)
                _repos.value = results
            }catch (e: Exception){
                _repos.value = repo.getCachedRepos()
            }finally {
                _loading.value = false
            }
        }
    }
}