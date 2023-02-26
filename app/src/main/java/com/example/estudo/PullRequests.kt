package com.example.estudo

import com.google.gson.annotations.SerializedName

data class PullRequests (
    val title: String,
    val body: String,
    val user: Owner,
    val state: String,
    @SerializedName("html_url")
    val htmlUrl: String
) :java.io.Serializable


