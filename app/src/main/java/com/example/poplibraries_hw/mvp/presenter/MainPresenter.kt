package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.view.MainView
import com.example.poplibraries_hw.navigation.UsersScreen
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen())
    }

    fun backClick() {
        router.exit()
    }


}