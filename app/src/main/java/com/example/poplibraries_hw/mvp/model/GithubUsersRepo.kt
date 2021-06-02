package com.example.poplibraries_hw.mvp.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val repositoriesRX = Observable.just(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsersRX() = repositoriesRX

}