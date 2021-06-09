package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.model.GithubUser
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersRepo
import com.example.poplibraries_hw.mvp.view.UserView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val userLogin: String,
    val mainThreadScheduler: Scheduler,
    val usersRepo: IGithubUsersRepo,
    val router: Router
    ):MvpPresenter<UserView>() {
    private var disposable = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable += usersRepo
            .getUserByLogin(userLogin)
            .observeOn(mainThreadScheduler)
            .subscribe(
                ::onLoadDataSuccess,
                ::onLoadDataError
            )

    }
    private fun onLoadDataError(error: Throwable) {
        router.exit()
    }

    private fun onLoadDataSuccess(user: GithubUser) {
        viewState.showUser(user)
    }
    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}