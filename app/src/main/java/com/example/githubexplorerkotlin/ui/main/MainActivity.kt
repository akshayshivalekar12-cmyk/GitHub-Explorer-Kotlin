package com.example.githubexplorerkotlin.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubexplorerkotlin.R
import com.example.githubexplorerkotlin.data.local.DatabaseProvider
import com.example.githubexplorerkotlin.data.repository.MainRepository
import com.example.githubexplorerkotlin.ui.adapter.RepoAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var repoAdapter: RepoAdapter
    private lateinit var mainViewModel: MainViewModel
    var q : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUi()
        setupViewModel()
        setupObservers()
    }

    private fun setupUi(){
        val rvRepo = findViewById<RecyclerView>(R.id.rvRepo)

        repoAdapter = RepoAdapter(
            repos = emptyList(),
            onItemClick = { repo ->
                Toast.makeText(this, repo.name, Toast.LENGTH_SHORT).show()
            },
            onFavClick = { repo ->
                mainViewModel.toggleFavourite(repo)
            }
        )

        rvRepo.layoutManager = LinearLayoutManager(this)
        rvRepo.adapter = repoAdapter

        val search = findViewById<SearchView>(R.id.searchView)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { mainViewModel.searchRepos(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    private fun setupViewModel(){
        val dao = DatabaseProvider.getDatabase(this).repoDao()
        val repository = MainRepository(dao)

        mainViewModel = ViewModelProvider(
            this,
            VMFactory(repository)
        )[MainViewModel::class.java]
    }

    private fun setupObservers(){
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)

        mainViewModel.loading.observe(this) { loading ->
            progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        mainViewModel.repos.observe(this) { repos ->
            repoAdapter.updateRepos(repos)
        }
    }

}