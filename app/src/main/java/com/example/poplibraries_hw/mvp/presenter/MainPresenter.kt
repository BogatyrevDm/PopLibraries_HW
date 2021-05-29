package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.Screens
import com.example.poplibraries_hw.mvp.view.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClick() {
        router.exit()
    }


}