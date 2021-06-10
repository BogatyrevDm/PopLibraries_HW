package com.example.poplibraries_hw.mvp.model

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}