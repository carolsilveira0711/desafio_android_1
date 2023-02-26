package com.example.estudo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class PullRequestsAdapter (
    private val body: List<PullRequests>,
    private val context: Context
    ) : RecyclerView.Adapter<PullRequestsAdapter.ItemViewHolder> () {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PullRequestsAdapter.ItemViewHolder {

        val  view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_pull_requests,
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

    class ItemViewHolder (private val view:View) : RecyclerView.ViewHolder(view){

        fun bind (pullRequests: PullRequests, context: Context){

            itemView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pullRequests.htmlUrl))

                context.startActivity(browserIntent)
            }

            val tvPullRequestsTitle = view.findViewById<TextView>(R.id.item_pull_requests_title)
            tvPullRequestsTitle.text = pullRequests.title

            val tvPullRequestsBody = view.findViewById<TextView>(R.id.item_pull_requests_body)
            tvPullRequestsBody.text = pullRequests.body

            val imPullRequestsImage = view.findViewById<ImageView>(R.id.item_pull_requests_image)


            Glide
                .with(context)
                .load(pullRequests.user.avatarUrl)
                .into(imPullRequestsImage)

            val tvPullRequestsLogin = view.findViewById<TextView>(R.id.item_pull_requests_login)
            tvPullRequestsLogin.text = pullRequests.user.login

        }

        val tvPullTitle = view

    }

}