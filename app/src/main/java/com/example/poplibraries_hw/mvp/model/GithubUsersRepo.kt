package com.example.poplibraries_hw.mvp.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val repositoriesRX = Observable.just(
        GithubUser("login1", "login1","",""),
        GithubUser("login2","login2","","")
    )

    fun getUsersRX() = repositoriesRX

}