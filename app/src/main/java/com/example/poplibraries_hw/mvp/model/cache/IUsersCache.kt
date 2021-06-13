package com.example.poplibraries_hw.mvp.model.cache

import com.example.poplibraries_hw.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUsersCache {
    fun putUsers(users:List<GithubUser>):Completable
    fun getUsers():Single<List<GithubUser>>
    fun getUser(login:String):Single<GithubUser>
}