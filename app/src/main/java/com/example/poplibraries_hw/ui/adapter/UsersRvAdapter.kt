package com.example.poplibraries_hw.mvp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.poplibraries_hw.databinding.ItemUserBinding
import com.example.poplibraries_hw.mvp.model.image.IImageLoader
import com.example.poplibraries_hw.mvp.presenter.IUserListPresenter

class UsersRVAdapter(val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos = -1
        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override fun loadImage(url: String, login:String) {
            imageLoader.loadInto(url,login, vb.ivImage)
        }

    }
}