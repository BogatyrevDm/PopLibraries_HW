package com.example.poplibraries_hw.mvp.view

import com.example.poplibraries_hw.mvp.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun showUser(user: GithubUser)

    fun showError(message: String?)
    fun init()
    fun updateReposList()
}