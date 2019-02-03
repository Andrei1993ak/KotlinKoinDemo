package com.github.andrei1993ak.mentoring.koin.navigation

import android.content.Context
import com.github.andrei1993ak.mentoring.koin.repository.User

interface AppNavigator {
    fun goToDetailPage(user: User, context: Context)
}


