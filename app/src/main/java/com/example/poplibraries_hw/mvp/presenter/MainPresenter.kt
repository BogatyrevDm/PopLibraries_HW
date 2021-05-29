package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.model.CountersModel
import com.example.poplibraries_hw.mvp.view.MainView
import moxy.MvpPresenter

class MainPresenter(val model:CountersModel) : MvpPresenter<MainView>() {

    fun counter1Click() {
        val nextValue = model.next(0)
        viewState.setButton1Text(nextValue.toString())
    }

    fun counter2Click() {
        val nextValue = model.next(1)
        viewState.setButton2Text(nextValue.toString())
    }

    fun counter3Click() {
        val nextValue = model.next(2)
        viewState.setButton3Text(nextValue.toString())
    }

}