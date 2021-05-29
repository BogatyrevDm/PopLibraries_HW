package com.example.poplibraries_hw.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(val login: String) : Parcelable
