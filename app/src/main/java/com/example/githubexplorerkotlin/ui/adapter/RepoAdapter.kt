package com.example.githubexplorerkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubexplorerkotlin.R
import com.example.githubexplorerkotlin.data.model.repo.Item

class RepoAdapter(
    private var repos: List<Item>,
    private val onItemClick : (Item) -> Unit,
    private val onFavClick: (Item) -> Unit) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
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
        holder.title.text = repo.name
        holder.name.text = repo.description
        val avatar = repo.owner?.avatarUrl

        holder.favIcon.setImageResource(
           if(repo.isFavourite) R.drawable.baseline_favorite_24
           else R.drawable.baseline_favorite_border_24)


        if (!avatar.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(avatar)
                .placeholder(R.drawable.ic_launcher_background)  // optional
                .into(holder.avatar)
        } else {
            holder.avatar.setImageResource(R.drawable.ic_launcher_background)
        }

        holder.itemView.setOnClickListener {
            onItemClick(repo)
        }

        holder.favIcon.setOnClickListener {
            onFavClick(repo)
        }
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
        val favIcon = itemView.findViewById<ImageButton>(R.id.favIcon)
    }
}