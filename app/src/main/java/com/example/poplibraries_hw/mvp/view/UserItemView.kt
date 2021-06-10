package com.example.poplibraries_hw.mvp.view

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun loadImage(url: String)
}