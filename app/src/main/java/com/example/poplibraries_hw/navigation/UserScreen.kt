package com.example.poplibraries_hw.navigation

import com.example.poplibraries_hw.ui.fragment.UserFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class UserScreen(private val userLogin: String) : SupportAppScreen() {
    override fun getFragment() = UserFragment.newInstance(userLogin)
}