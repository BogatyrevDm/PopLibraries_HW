package com.example.poplibraries_hw.mvp.model

import com.google.gson.annotations.SerializedName

class GitHubRepo (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("forks_count") val forksCount: String
)