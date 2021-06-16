package com.example.poplibraries_hw.di

import com.example.poplibraries_hw.di.modules.*
import com.example.poplibraries_hw.mvp.presenter.MainPresenter
import com.example.poplibraries_hw.mvp.presenter.RepoPresenter
import com.example.poplibraries_hw.mvp.presenter.UserPresenter
import com.example.poplibraries_hw.mvp.presenter.UsersPresenter
import com.example.poplibraries_hw.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        NavigationModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repoPresenter: RepoPresenter)
}