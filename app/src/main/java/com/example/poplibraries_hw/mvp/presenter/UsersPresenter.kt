package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.model.GithubUser
import com.example.poplibraries_hw.mvp.model.GithubUsersRepo
import com.example.poplibraries_hw.mvp.view.UserItemView
import com.example.poplibraries_hw.mvp.view.UsersView
import com.example.poplibraries_hw.navigation.UserScreen
import io.reactivex.rxjava3.core.Single
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            Single.just(users[view.pos]).subscribe({
                onBindViewSuccess(view, it.login)
            }, ::onBindViewError)
        }

        private fun onBindViewSuccess(view: UserItemView, login: String) {
            view.setLogin(login)
        }

        private fun onBindViewError(error: Throwable) {

        }
    }

    val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(UserScreen(usersListPresenter.users[itemView.pos].login))
        }
    }

    fun loadData() {
        usersRepo.getUsersRX().doOnComplete(
            ::onLoadDataComplete
        ).subscribe(
            ::onLoadDataSuccess
        )
    }

    private fun onLoadDataComplete() {
        viewState.updateList()
    }

    private fun onLoadDataSuccess(user: GithubUser) {
        usersListPresenter.users.add(user)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}