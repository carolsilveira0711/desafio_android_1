package com.example.estudo

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PullRequestsActivity: AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    lateinit var rvPullRequests: RecyclerView
    lateinit var pullRequestsAdapter: PullRequestsAdapter
    var teste: String = "teste"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        progressBar = findViewById<ProgressBar>(R.id.progress_Bar) as ProgressBar

        val repository = intent.getSerializableExtra("1") as Repository

        rvPullRequests = findViewById<RecyclerView>(R.id.rv_pull_requests)
        rvPullRequests.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getData()

        val toolbar = findViewById<Toolbar>(R.id.pull_requests_toolbar)
        toolbar.title = repository.name

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.github.com/")

        val repository = intent.getSerializableExtra("1") as Repository

        val endpoint = retrofitClient.create(APIservice::class.java)
        val callback = endpoint.getPulls(repository.owner.login, repository.name)

        callback.enqueue(object : Callback<List<PullRequests>> {
            override fun onFailure(call: Call<List<PullRequests>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<PullRequests>>, response: Response<List<PullRequests>>){
                progressBar?.visibility = View.GONE
                pullRequestsAdapter = PullRequestsAdapter(response.body()!!, this@PullRequestsActivity)
                rvPullRequests.adapter = pullRequestsAdapter
            }
        })

    }
}