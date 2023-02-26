package com.example.estudo

import com.google.gson.annotations.SerializedName

data class Owner(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
) :java.io.Serializable