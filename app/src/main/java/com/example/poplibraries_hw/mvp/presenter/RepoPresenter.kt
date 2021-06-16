package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.model.entity.GitHubRepo
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersReposRepo
import com.example.poplibraries_hw.mvp.view.RepoView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepoPresenter(
    private val userLogin: String,
    private val repoUrl: String
) : MvpPresenter<RepoView>() {

    private var disposable = CompositeDisposable()


    @Inject lateinit var mainThreadScheduler: Scheduler
    @Inject lateinit var ReposRepo: IGithubUsersReposRepo
    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable += ReposRepo
            .getUserRepoByName(userLogin, repoUrl)
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
        viewState.showRepoId(repo.id)
        viewState.showRepoName(repo.name)
        viewState.showRepoDescription(repo.description)
        viewState.showRepoForksCount(repo.forksCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}