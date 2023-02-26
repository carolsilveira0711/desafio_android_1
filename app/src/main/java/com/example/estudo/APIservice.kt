package com.example.estudo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIservice {
    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepositories() : Call<ObjectListResponse>

    @GET("repos/{login}/{name}/pulls")
    fun getPulls(@Path("login")login:String, @Path("name")name:String) : Call<List<PullRequests>>

}