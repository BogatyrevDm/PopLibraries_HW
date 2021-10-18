package com.example.poplibraries_hw.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.poplibraries_hw.R.layout.fragment_user
import com.example.poplibraries_hw.databinding.FragmentRepoBinding
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersReposRepo
import com.example.poplibraries_hw.mvp.presenter.RepoPresenter
import com.example.poplibraries_hw.mvp.view.RepoView
import com.example.poplibraries_hw.ui.AbsFragment
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class RepoFragment : AbsFragment(fragment_user), RepoView {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private val repoUrl by lazy {
        arguments?.getString(ARG_REPO_URL) ?: ""
    }
    private val userLogin by lazy {
        arguments?.getString(ARG_USER_LOGIN) ?: ""
    }

    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var reposRepo: IGithubUsersReposRepo
    @Inject
    lateinit var router: Router
    private val presenter by moxyPresenter {
        RepoPresenter(
            userLogin,
            repoUrl,
            mainThreadScheduler,
            reposRepo,
            router,
            this
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