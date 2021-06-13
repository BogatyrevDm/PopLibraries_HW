package com.example.poplibraries_hw.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, login:String, container: T)
}