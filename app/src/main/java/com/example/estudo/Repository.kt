package com.example.estudo

import com.google.gson.annotations.SerializedName

data class Repository (

    @SerializedName("full_name")
    val fullName:String,
    @SerializedName("forks_count")
    val forksCount:Int,
    @SerializedName("stargazers_count")
    val stargazersCount:Int,

    val description:String,
    val owner: Owner,
    val name: String
) : java.io.Serializable