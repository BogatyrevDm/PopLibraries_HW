package com.example.poplibraries_hw.mvp.model.api

import com.example.poplibraries_hw.mvp.model.GitHubRepo
import com.example.poplibraries_hw.mvp.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface IDataSource {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<GithubUser>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GitHubRepo>>

    @GET("/repos/{login}/{name}")
    fun getUserRepoByName(@Path("login") login: String, @Path("name") name: String): Single<GitHubRepo>
}
