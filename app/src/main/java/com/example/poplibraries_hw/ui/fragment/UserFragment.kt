package com.example.poplibraries_hw.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.poplibraries_hw.databinding.FragmentUserBinding
import com.example.poplibraries_hw.mvp.presenter.UserPresenter
import com.example.poplibraries_hw.mvp.view.UserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val userLogin by lazy {
        arguments?.getString(ARG_USER_LOGIN) ?: ""
    }
    private val presenter by moxyPresenter { UserPresenter(userLogin) }

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

    override fun showUser(userLogin: String) {
        binding.userLogin.text = userLogin
    }
}