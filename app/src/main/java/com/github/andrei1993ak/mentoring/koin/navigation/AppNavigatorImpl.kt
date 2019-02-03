package com.github.andrei1993ak.mentoring.koin.navigation

import android.content.Context
import android.content.Intent
import com.github.andrei1993ak.mentoring.koin.repository.User
import com.github.andrei1993ak.mentoring.koin.ui.details.UserDetailsActivity
import com.github.andrei1993ak.mentoring.koin.utils.USER_EXTRA_KEY

class AppNavigatorImpl : AppNavigator {
    override fun goToDetailPage(user: User, context: Context) {
        val intent = Intent(context, UserDetailsActivity::class.java)
        intent.putExtra(USER_EXTRA_KEY, user)

        context.startActivity(intent)
    }
}