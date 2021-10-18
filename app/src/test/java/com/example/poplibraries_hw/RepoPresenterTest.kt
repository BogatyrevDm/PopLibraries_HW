package com.example.poplibraries_hw

import com.example.poplibraries_hw.mvp.model.entity.GitHubRepo
import com.example.poplibraries_hw.mvp.model.repo.IGithubUsersReposRepo
import com.example.poplibraries_hw.mvp.presenter.RepoPresenter
import com.example.poplibraries_hw.mvp.view.RepoView
import io.reactivex.rxjava3.core.Scheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class RepoPresenterTest {
    private lateinit var presenter: RepoPresenter

    @Mock
    private lateinit var mainThreadScheduler: Scheduler

    @Mock
    private lateinit var reposRepo: IGithubUsersReposRepo
    @Mock
    private lateinit var viewContract: RepoView

    @Mock
    private lateinit var router: Router

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = RepoPresenter(
            "Some login", "Some password", mainThreadScheduler,
            reposRepo, router, viewContract
        )
    }

    @Test
    fun onLoadDataSuccess_Test() {
        val repo = mock(GitHubRepo::class.java)
        `when`(repo.id).thenReturn("Some id")
        `when`(repo.name).thenReturn("Some name")
        `when`(repo.description).thenReturn("Some description")
        `when`(repo.forksCount).thenReturn("Some forks count")
        presenter.onLoadDataSuccess(repo)
        verify(viewContract, times(1)).showRepoId("Some id")
    }

    @Test
    fun onLoadDataError_Test() {
        val error = mock(Throwable::class.java)

        presenter.onLoadDataError(error)
        verify(router, times(1)).exit()
    }

    @Test
    fun RepoIdEquals_Test() {
        val repo = mock(GitHubRepo::class.java)
        `when`(repo.id).thenReturn("Some id")
        assertEquals(repo.id, "Some id")
    }
}