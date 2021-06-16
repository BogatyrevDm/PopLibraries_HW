package com.example.poplibraries_hw.di.modules

import com.example.poplibraries_hw.mvp.model.api.IDataSource
import com.example.poplibraries_hw.mvp.model.cache.IUsersCache
import com.example.poplibraries_hw.mvp.model.cache.IUsersReposCache
import com.example.poplibraries_hw.mvp.model.network.INetworkStatus
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersRepo
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersReposRepo
import com.example.poplibraries_hw.mvp.model.repo.RetrofitGithubReposRepo
import com.example.poplibraries_hw.mvp.model.repo.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, cache: IUsersCache, networkStatus: INetworkStatus) : IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun usersReposRepo(api: IDataSource, cache: IUsersReposCache, networkStatus: INetworkStatus) : IGithubUsersReposRepo = RetrofitGithubReposRepo(api, networkStatus, cache)

}