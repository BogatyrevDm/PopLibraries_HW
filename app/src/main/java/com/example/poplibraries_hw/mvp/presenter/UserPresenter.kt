package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.model.GitHubRepo
import com.example.poplibraries_hw.mvp.model.GithubUser
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersRepo
import com.example.poplibraries_hw.mvp.view.RepoItemView
import com.example.poplibraries_hw.mvp.view.UserItemView
import com.example.poplibraries_hw.mvp.view.UserView
import com.example.poplibraries_hw.navigation.RepoScreen
import com.example.poplibraries_hw.navigation.UserScreen
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
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



    class ReposListPresenter : IRepoListPresenter {
        val repos = mutableListOf<GitHubRepo>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null
        override fun getCount() = repos.size
        override fun bindView(view: RepoItemView) {
            Single.just(repos[view.pos]).subscribe({
                onBindViewSuccess(view, it.name)
            }, ::onBindViewError)
        }

        private fun onBindViewSuccess(view: RepoItemView, name: String) {
            view.setName(name)
        }

        private fun onBindViewError(error: Throwable) {

        }
    }
    val reposListPresenter = UserPresenter.ReposListPresenter()
    private var disposable = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        disposable += usersRepo
            .getUserByLogin(userLogin)
            .observeOn(mainThreadScheduler)
            .subscribe(
                ::onLoadDataSuccess,
                ::onLoadDataError
            )
        reposListPresenter.itemClickListener = { itemView ->
            router.navigateTo(RepoScreen(userLogin,reposListPresenter.repos[itemView.pos].name))
        }

    }
    private fun onLoadDataError(error: Throwable) {
        router.exit()
    }

    private fun onLoadDataSuccess(user: GithubUser) {
        viewState.showUser(user)
        disposable += usersRepo
            .getReposByUrl(user.reposUrl)
            .observeOn(mainThreadScheduler)
            .subscribe(
                ::onLoadReposSuccess,
                ::onLoadDataError)

    }

    private fun onLoadReposSuccess(repos: List<GitHubRepo>) {
        reposListPresenter.repos.clear()
        reposListPresenter.repos.addAll(repos)
        viewState.updateReposList()
    }
    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}