package com.example.poplibraries_hw.mvp.model.repo

import com.example.poplibraries_hw.mvp.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}