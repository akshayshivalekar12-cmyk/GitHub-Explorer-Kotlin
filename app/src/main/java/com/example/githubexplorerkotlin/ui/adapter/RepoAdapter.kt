package com.example.githubexplorerkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubexplorerkotlin.R
import com.example.githubexplorerkotlin.data.model.Item

class RepoAdapter(private var repos: List<Item>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {
        return RepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(
        holder: RepoViewHolder,
        position: Int
    ) {
        val repo = repos[position]
//        holder.title.text = repo.name
        holder.name.text = repo.full_name
    }

    override fun getItemCount() = repos.size

    fun updateRepos(newRepos: List<Item>){
        repos = newRepos
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val avatar = itemView.findViewById<ImageView>(R.id.avatar)
        val title = itemView.findViewById<TextView>(R.id.title)
        val name = itemView.findViewById<TextView>(R.id.name)
    }
}