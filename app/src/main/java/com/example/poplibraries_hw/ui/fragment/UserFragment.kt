package com.example.poplibraries_hw.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.poplibraries_hw.databinding.FragmentUserBinding
import com.example.poplibraries_hw.mvp.model.GithubUser
import com.example.poplibraries_hw.mvp.model.GithubUsersRepo
import com.example.poplibraries_hw.mvp.model.api.ApiHolder
import com.example.poplibraries_hw.mvp.model.repo.RetrofitGithubUsersRepo
import com.example.poplibraries_hw.mvp.presenter.UserPresenter
import com.example.poplibraries_hw.mvp.view.UserView
import com.example.poplibraries_hw.ui.App
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val userLogin by lazy {
        arguments?.getString(ARG_USER_LOGIN) ?: ""
    }
    private val presenter by moxyPresenter {
        UserPresenter(userLogin,AndroidSchedulers.mainThread(),
        RetrofitGithubUsersRepo(ApiHolder.api),
        App.instance.router) }

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

    override fun showUser(user: GithubUser) {
        binding.userLogin.text = user.login
    }

    override fun showError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }
}