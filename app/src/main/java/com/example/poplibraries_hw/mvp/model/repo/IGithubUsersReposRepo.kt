package com.example.poplibraries_hw.mvp.model.repo

import com.example.poplibraries_hw.mvp.model.entity.GitHubRepo
import com.example.poplibraries_hw.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersReposRepo {
    fun getReposByUrl(url:String?, login: String): Single<List<GitHubRepo>>
    fun getUserRepoByName(login: String, name: String): Single<GitHubRepo>
}