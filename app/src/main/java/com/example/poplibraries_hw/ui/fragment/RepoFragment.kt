package com.example.poplibraries_hw.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.poplibraries_hw.databinding.FragmentRepoBinding
import com.example.poplibraries_hw.mvp.model.api.ApiHolder
import com.example.poplibraries_hw.mvp.model.cache.RoomUsersReposCache
import com.example.poplibraries_hw.mvp.model.repo.RetrofitGithubReposRepo
import com.example.poplibraries_hw.mvp.presenter.RepoPresenter
import com.example.poplibraries_hw.mvp.view.RepoView
import com.example.poplibraries_hw.ui.App
import com.example.poplibraries_hw.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
        RepoPresenter(
            userLogin,
            repoUrl).apply {
                App.component.inject(this)
        }
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

    override fun showRepoId(id: String) {
        binding.repoId.text = id
    }
    override fun showRepoName(name: String) {
        binding.repoName.text = name
    }
    override fun showRepoDescription(description: String) {
        binding.repoDescription.text = description
    }
    override fun showRepoForksCount(forksCount: String) {
        binding.forksCount.text = forksCount
    }
    override fun showError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }
}