package com.example.poplibraries_hw.mvp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poplibraries_hw.databinding.ItemRepoBinding
import com.example.poplibraries_hw.mvp.presenter.IRepoListPresenter

class ReposRVAdapter(val presenter: IRepoListPresenter) :
    RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override var pos = -1
        override fun setName(text: String) = with(vb) {
            tvName.text = text
        }
    }
}