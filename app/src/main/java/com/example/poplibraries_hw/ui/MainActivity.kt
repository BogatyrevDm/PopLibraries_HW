package com.example.poplibraries_hw.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibraries_hw.databinding.ActivityMainBinding
import com.example.poplibraries_hw.mvp.model.CountersModel
import com.example.poplibraries_hw.mvp.model.GithubUsersRepo
import com.example.poplibraries_hw.mvp.presenter.MainPresenter
import com.example.poplibraries_hw.mvp.view.MainView
import com.example.poplibraries_hw.mvp.view.UsersRVAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }
    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

}