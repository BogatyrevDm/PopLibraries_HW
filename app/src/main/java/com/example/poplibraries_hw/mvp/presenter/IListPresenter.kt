package com.example.poplibraries_hw.mvp.presenter

import com.example.poplibraries_hw.mvp.view.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener:((V) -> Unit)?
    fun bindView(view:V)
    fun getCount():Int
}
