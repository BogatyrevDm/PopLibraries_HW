package com.example.poplibraries_hw.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun showUserId(id: String)
    fun showUserLogin(login: String)
    fun showUserRepoUrl(userRepoUrl: String)
    fun showError(message: String?)
    fun init()
    fun updateReposList()
}