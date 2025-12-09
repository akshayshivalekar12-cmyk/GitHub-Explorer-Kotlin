package com.example.githubexplorerkotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubexplorerkotlin.data.model.repo.Item
import com.example.githubexplorerkotlin.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _repos = MutableLiveData<List<Item>>()
    val repos : LiveData<List<Item>> = _repos

    fun searchRepos(query: String){
        viewModelScope.launch {
            try {
                _loading.value = true
                val results = repository.searchRepos(query)
                _repos.value = results
            }catch (e: Exception){
                _repos.value = repository.getCachedRepos()
            }finally {
                _loading.value = false
            }
        }
    }

    fun toggleFavourite(repo: Item){
        viewModelScope.launch {
            val newState = !repo.isFavourite
            repository.toggleFavourites(repo)
            repo.isFavourite = newState
            _repos.value = _repos.value
        }
    }
}