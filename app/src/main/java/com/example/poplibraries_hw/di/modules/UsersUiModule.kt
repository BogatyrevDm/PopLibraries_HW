package com.example.poplibraries_hw.di.modules

import com.example.poplibraries_hw.ui.fragment.RepoFragment
import com.example.poplibraries_hw.ui.fragment.UserFragment
import com.example.poplibraries_hw.ui.fragment.UsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserUiModule {

    @ContributesAndroidInjector
    abstract fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    abstract fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun bindRepoFragment(): RepoFragment

}