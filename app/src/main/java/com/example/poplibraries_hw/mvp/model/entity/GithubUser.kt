package com.example.poplibraries_hw.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    @SerializedName("id") val id: String,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("repos_url") val reposUrl: String
) : Parcelable
