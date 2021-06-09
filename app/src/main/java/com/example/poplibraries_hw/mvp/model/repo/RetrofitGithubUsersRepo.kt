package com.example.poplibraries_hw.mvp.model.repo

import com.example.poplibraries_hw.mvp.model.api.IDataSource
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {

    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUserByLogin(login:String) = api.getUser(login).subscribeOn(Schedulers.io())
}