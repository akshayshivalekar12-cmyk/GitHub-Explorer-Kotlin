package com.example.githubexplorerkotlin.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubexplorerkotlin.R
import com.example.githubexplorerkotlin.data.local.DatabaseProvider
import com.example.githubexplorerkotlin.data.repository.MainRepository
import com.example.githubexplorerkotlin.ui.adapter.RepoAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var repoAdapter: RepoAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showRepos()
    }

    private fun showRepos() {
        val dao = DatabaseProvider.getDatabase(this).repoDao()
        val repo = MainRepository(dao)
        mainViewModel = ViewModelProvider.create(this, MainVMFactory(repo))[MainViewModel::class.java]

        val rvRepo = findViewById<RecyclerView>(R.id.rvRepo)
        repoAdapter = RepoAdapter(arrayListOf())
        rvRepo.layoutManager = LinearLayoutManager(this)
        rvRepo.adapter = repoAdapter

        val progressBar = findViewById<ProgressBar>(R.id.progressbar)

        val search = findViewById<SearchView>(R.id.searchView)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) mainViewModel.searchRepos(query)
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        mainViewModel.repos.observe(this){repos ->
            repoAdapter.updateRepos(repos)
        }

        mainViewModel.loading.observe(this){loading ->
            progressBar.visibility = if(loading) View.VISIBLE else View.GONE
        }

    }
}