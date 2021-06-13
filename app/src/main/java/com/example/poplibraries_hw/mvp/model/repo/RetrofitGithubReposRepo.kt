package com.example.poplibraries_hw.mvp.model.repo

import com.example.poplibraries_hw.mvp.model.api.IDataSource
import com.example.poplibraries_hw.mvp.model.cache.IUsersReposCache
import com.example.poplibraries_hw.mvp.model.entity.GitHubRepo
import com.example.poplibraries_hw.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubReposRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IUsersReposCache
) : IGithubUsersReposRepo {
    //    override fun getReposByUrl(url: String) = api.getUserRepos(url).subscribeOn(Schedulers.io())
    override fun getReposByUrl(url: String?, login: String) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                url?.let { url ->
                    api.getUserRepos(url).flatMap { repositories ->
                        cache.putRepos(login, repositories)
                            .andThen(Single.just(repositories))
                    }
                } ?: Single.error<List<GitHubRepo>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())

            } else {
                cache.getRepos(login)
            }
        }.subscribeOn(Schedulers.io())

    override fun getUserRepoByName(login: String, name: String) =
        api.getUserRepoByName(login, name).subscribeOn(Schedulers.io())
}