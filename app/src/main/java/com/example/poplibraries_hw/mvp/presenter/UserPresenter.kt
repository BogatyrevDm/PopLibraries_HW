package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.view.UserView
import moxy.MvpPresenter

class UserPresenter(val userLogin: String):MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showUser(userLogin)
    }

}