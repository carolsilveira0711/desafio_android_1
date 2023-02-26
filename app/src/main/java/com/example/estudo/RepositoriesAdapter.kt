package com.example.estudo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RepositoriesAdapter(
    private val body: List<Repository>,
    private val context: Context
    ) : RecyclerView.Adapter<RepositoriesAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_repository,
            parent,
            false
        )

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return body.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(body[position], context)
    }

    class ItemViewHolder(private val view:View) : RecyclerView.ViewHolder(view){

        fun bind(repository: Repository, context: Context){

            itemView.setOnClickListener {
                val intent = Intent(context, PullRequestsActivity::class.java)

                intent.putExtra("1", repository)

                context.startActivity(intent)

            }

            val tvRepositoryName = view.findViewById<TextView>(R.id.item_repository_name)
            tvRepositoryName.text = repository.fullName

            val tvRepositoryDescription = view.findViewById<TextView>(R.id.item_repository_description)
            tvRepositoryDescription.text = repository.description

            val imRepositoryImage = view.findViewById<ImageView>(R.id.item_repository_image)

            val tvRepositoryFork = view.findViewById<TextView>(R.id.item_repository_fork)
            tvRepositoryFork.text = repository.forksCount.toString()

            val tvRepositoryStars = view.findViewById<TextView>(R.id.item_repository_star)
            tvRepositoryStars.text = repository.stargazersCount.toString()

            val tvRepositoryLogin = view.findViewById<TextView>(R.id.item_repository_login)
            tvRepositoryLogin.text = repository.owner.login


            Glide
                .with(context)
                .load(repository.owner.avatarUrl)
                .into(imRepositoryImage)

        }
    }

}
