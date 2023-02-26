package com.example.estudo

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class RepositoriesActivity: Activity() {

    private var progressBar: ProgressBar? = null
    lateinit var rvRepositories:RecyclerView
    lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById<ProgressBar>(R.id.progress_Bar) as ProgressBar


        rvRepositories = findViewById<RecyclerView>(R.id.rv_repositories)
        rvRepositories.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        getData()


    }

    override fun onResume() {
        super.onResume()
    }

    fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.github.com/")

        val endpoint = retrofitClient.create(APIservice::class.java)
        val callback = endpoint.getRepositories()

        callback.enqueue(object : Callback<ObjectListResponse> {
            override fun onFailure(call: Call<ObjectListResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ObjectListResponse>, response: Response<ObjectListResponse>) {
                progressBar?.visibility = View.GONE
                repositoriesAdapter = RepositoriesAdapter(response.body()!!.items, this@RepositoriesActivity)
                rvRepositories.adapter = repositoriesAdapter
            }
        })
    }



}