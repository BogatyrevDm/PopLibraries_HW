package com.example.poplibraries_hw.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibraries_hw.R.layout.fragment_users
import com.example.poplibraries_hw.databinding.FragmentUsersBinding
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersRepo
import com.example.poplibraries_hw.mvp.presenter.UsersPresenter
import com.example.poplibraries_hw.mvp.view.UsersRVAdapter
import com.example.poplibraries_hw.mvp.view.UsersView
import com.example.poplibraries_hw.ui.AbsFragment
import com.example.poplibraries_hw.ui.BackButtonListener
import com.example.poplibraries_hw.ui.image.GlideImageLoader
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class UsersFragment : AbsFragment(fragment_users), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var router: Router

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(usersRepo, mainThreadScheduler, router)
    }
    var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(
            presenter.usersListPresenter,
            GlideImageLoader()
        )
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}