package com.example.poplibraries_hw.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibraries_hw.R.layout.fragment_user
import com.example.poplibraries_hw.databinding.FragmentUserBinding
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersRepo
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersReposRepo
import com.example.poplibraries_hw.mvp.presenter.UserPresenter
import com.example.poplibraries_hw.mvp.view.ReposRVAdapter
import com.example.poplibraries_hw.mvp.view.UserView
import com.example.poplibraries_hw.ui.AbsFragment
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class UserFragment : AbsFragment(fragment_user), UserView {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val userLogin by lazy {
        arguments?.getString(ARG_USER_LOGIN) ?: ""
    }
    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var reposRepo: IGithubUsersReposRepo
    @Inject
    lateinit var router: Router

    private val presenter by moxyPresenter {
        UserPresenter(
            userLogin,
            usersRepo,
            mainThreadScheduler,
            reposRepo,
            router
        )
    }

    var adapter: ReposRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_USER_LOGIN = "USER_LOGIN"
        fun newInstance(userLogin: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_LOGIN, userLogin)
                }
            }
    }

    override fun showUserLogin(login: String) {
        binding.userLogin.text = login
    }
    override fun showUserId(id: String) {
        binding.userId.text = id
    }
    override fun showUserRepoUrl(userRepoUrl: String) {
        binding.userRepoText.text = userRepoUrl
    }
    override fun showError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    override fun init() {
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        binding.rvRepos.adapter = adapter
    }

    override fun updateReposList() {
        adapter?.notifyDataSetChanged()
    }

}