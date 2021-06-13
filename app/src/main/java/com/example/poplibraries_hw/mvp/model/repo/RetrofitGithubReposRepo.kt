package com.example.poplibraries_hw.mvp.model.repo

import com.example.poplibraries_hw.mvp.model.api.IDataSource
import com.example.poplibraries_hw.mvp.model.entity.GitHubRepo
import com.example.poplibraries_hw.mvp.model.entity.GithubUser
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubRepo
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubUser
import com.example.poplibraries_hw.mvp.model.network.INetworkStatus
import com.example.poplibraries_hw.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubReposRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IGithubUsersReposRepo {
//    override fun getReposByUrl(url: String) = api.getUserRepos(url).subscribeOn(Schedulers.io())
override fun getReposByUrl(url:String?, login: String?) =
    networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            url?.let { url ->
                api.getUserRepos(url).flatMap { repositories ->
                    Single.fromCallable {
                        val roomUser = login?.let { db.userDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such users in database")
                        val roomRepos = repositories.map {
                            RoomGitHubRepo(
                                it.id ?: "",
                                it.name ?: "",
                                it.description?: "",
                                it.forksCount ?: "",
                                roomUser.id
                            )
                        }
                        db.repoDao.insert(roomRepos)
                        repositories
                    }
                }
            } ?: Single.error<List<GitHubRepo>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())

        } else {
            Single.fromCallable {
                val roomUser = login?.let { db.userDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such users in database")
                db.repoDao.findForUser(roomUser.id).map { GitHubRepo(it.id, it.name,it.description, it.forksCount) }
            }
        }
    }.subscribeOn(Schedulers.io())
    override fun getUserRepoByName(login: String, name: String) =
        api.getUserRepoByName(login, name).subscribeOn(Schedulers.io())
}