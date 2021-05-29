package com.example.poplibraries_hw.navigation

import com.example.poplibraries_hw.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class UsersScreen() : SupportAppScreen() {
    override fun getFragment() = UsersFragment.newInstance()
}