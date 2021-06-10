package com.example.poplibraries_hw.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibraries_hw.databinding.FragmentUsersBinding
import com.example.poplibraries_hw.mvp.model.api.ApiHolder
import com.example.poplibraries_hw.mvp.model.repo.RetrofitGithubUsersRepo
import com.example.poplibraries_hw.mvp.presenter.UsersPresenter
import com.example.poplibraries_hw.mvp.view.UsersRVAdapter
import com.example.poplibraries_hw.mvp.view.UsersView
import com.example.poplibraries_hw.ui.App
import com.example.poplibraries_hw.ui.BackButtonListener
import com.example.poplibraries_hw.ui.image.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment: MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    val presenter: UsersPresenter by moxyPresenter { UsersPresenter(
        AndroidSchedulers.mainThread(),
        RetrofitGithubUsersRepo(ApiHolder.api),
        App.instance.router) }
    var adapter: UsersRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.rvUsers.adapter = adapter
    }
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
    override fun backPressed() = presenter.backPressed()
}