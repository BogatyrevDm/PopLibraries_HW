package com.example.poplibraries_hw.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.poplibraries_hw.databinding.FragmentRepoBinding
import com.example.poplibraries_hw.mvp.model.GitHubRepo
import com.example.poplibraries_hw.mvp.model.api.ApiHolder
import com.example.poplibraries_hw.mvp.model.repo.RetrofitGithubUsersRepo
import com.example.poplibraries_hw.mvp.presenter.RepoPresenter
import com.example.poplibraries_hw.mvp.view.RepoView
import com.example.poplibraries_hw.ui.App
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class RepoFragment : MvpAppCompatFragment(), RepoView {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private val repoUrl by lazy {
        arguments?.getString(ARG_REPO_URL) ?: ""
    }
    private val userLogin by lazy {
        arguments?.getString(ARG_USER_LOGIN) ?: ""
    }
    private val presenter by moxyPresenter {
        RepoPresenter(userLogin,
                repoUrl, AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            App.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_USER_LOGIN = "USER_LOGIN"
        private const val ARG_REPO_URL = "REPO_URL"
        fun newInstance(userLogin: String, repoUrl: String) =
            RepoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_LOGIN, userLogin)
                    putString(ARG_REPO_URL, repoUrl)
                }
            }
    }

    override fun showRepo(repo: GitHubRepo) {
        binding.repoId.text = repo.id
        binding.repoName.text = repo.name
        binding.repoDescription.text = repo.description
        binding.forksCount.text = repo.forksCount
    }

    override fun showError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }
}