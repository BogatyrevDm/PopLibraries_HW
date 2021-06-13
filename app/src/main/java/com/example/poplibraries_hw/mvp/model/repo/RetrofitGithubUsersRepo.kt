package com.example.poplibraries_hw.mvp.model.repo

import com.example.poplibraries_hw.mvp.model.api.IDataSource
import com.example.poplibraries_hw.mvp.model.entity.GithubUser
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubUser
import com.example.poplibraries_hw.mvp.model.network.INetworkStatus
import com.example.poplibraries_hw.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    val roomUsers = users.map { user ->
                        RoomGitHubUser(
                            user.id ?: "",
                            user.login ?: "",
                            user.avatarUrl ?: "",
                            user.reposUrl ?: ""
                        )
                    }
                    db.userDao.insert(roomUsers)
                    users
                }
            }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
                }
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getUserByLogin(login: String) = api.getUser(login).subscribeOn(Schedulers.io())

}