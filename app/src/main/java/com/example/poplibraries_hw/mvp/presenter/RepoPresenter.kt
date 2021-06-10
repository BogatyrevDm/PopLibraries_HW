package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.model.GitHubRepo
import com.example.poplibraries_hw.mvp.model.GithubUser
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersRepo
import com.example.poplibraries_hw.mvp.view.RepoItemView
import com.example.poplibraries_hw.mvp.view.RepoView
import com.example.poplibraries_hw.mvp.view.UserView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepoPresenter(
    private val userLogin: String,
    private val repoUrl: String,
    val mainThreadScheduler: Scheduler,
    val usersRepo: IGithubUsersRepo,
    val router: Router
    ):MvpPresenter<RepoView>() {




    private var disposable = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable += usersRepo
            .getUserRepoByName(userLogin,repoUrl)
            .observeOn(mainThreadScheduler)
            .subscribe(
                ::onLoadDataSuccess,
                ::onLoadDataError
            )

    }
    private fun onLoadDataError(error: Throwable) {
        router.exit()
    }

    private fun onLoadDataSuccess(repo: GitHubRepo) {
        viewState.showRepo(repo)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}