package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.model.GithubUser
import com.example.poplibraries_hw.mvp.model.GithubUsersRepo
import com.example.poplibraries_hw.mvp.view.UserItemView
import com.example.poplibraries_hw.mvp.view.UsersView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router):MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            //TODO: переход на экран пользователя c помощью router.navigateTo
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}