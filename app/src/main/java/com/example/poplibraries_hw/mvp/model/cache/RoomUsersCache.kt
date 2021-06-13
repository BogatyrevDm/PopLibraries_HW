package com.example.poplibraries_hw.mvp.model.cache

import com.example.poplibraries_hw.mvp.model.entity.GithubUser
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubUser
import com.example.poplibraries_hw.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomUsersCache : IUsersCache {
    private val db = Database.getInstance()

    override fun putUsers(users: List<GithubUser>): Completable {

        val roomUsers = users.map { user ->
            RoomGitHubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
       return db.userDao.insert(roomUsers)

    }

    override fun getUsers() =
        Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }

    override fun getUser(login: String): Single<GithubUser> =
        Single.fromCallable {
            val roomUser = db.userDao.findByLogin(login)
            roomUser?.let {
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }

        }



}