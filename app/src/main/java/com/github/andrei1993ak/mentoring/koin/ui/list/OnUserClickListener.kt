package com.github.andrei1993ak.mentoring.koin.ui.list

import com.github.andrei1993ak.mentoring.koin.repository.User

interface OnUserClickListener {
    fun onUserClicked(user: User)
}