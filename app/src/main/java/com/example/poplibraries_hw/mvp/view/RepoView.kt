package com.example.poplibraries_hw.mvp.view

import com.example.poplibraries_hw.mvp.model.GitHubRepo
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface RepoView : MvpView {
    fun showRepo(repo: GitHubRepo)
    fun showError(message: String?)
}