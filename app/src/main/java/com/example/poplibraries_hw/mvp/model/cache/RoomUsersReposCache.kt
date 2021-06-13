package com.example.poplibraries_hw.mvp.model.cache

import com.example.poplibraries_hw.mvp.model.entity.GitHubRepo
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubRepo
import com.example.poplibraries_hw.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomUsersReposCache : IUsersReposCache {
    private val db = Database.getInstance()

    override fun putRepos(login:String, repos: List<GitHubRepo>): Completable {
        val roomUser = login.let { db.userDao.findByLogin(it) }
            ?: throw java.lang.RuntimeException("No such users in database")
        val roomRepos = repos.map {
            RoomGitHubRepo(
                it.id ?: "",
                it.name ?: "",
                it.description ?: "",
                it.forksCount ?: "",
                roomUser.id
            )
        }
       return db.repoDao.insert(roomRepos)
    }

    override fun getRepos(login:String) =
        Single.fromCallable {
            val roomUser = login.let { db.userDao.findByLogin(it) }
                ?: throw java.lang.RuntimeException("No such users in database")
            db.repoDao.findForUser(roomUser.id)
                .map { GitHubRepo(it.id, it.name, it.description, it.forksCount) }
        }

}