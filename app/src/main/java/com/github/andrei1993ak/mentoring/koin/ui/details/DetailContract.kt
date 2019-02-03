package com.github.andrei1993ak.mentoring.koin.ui.details

import android.content.Intent
import com.github.andrei1993ak.mentoring.koin.repository.User
import com.github.andrei1993ak.mentoring.koin.ui.mvp.BasePresenter
import com.github.andrei1993ak.mentoring.koin.ui.mvp.BaseView

interface DetailContract {
    interface View : BaseView<Presenter> {
        fun displayUser(user: User)
    }

    interface Presenter : BasePresenter<View> {
        fun getDetail(intent: Intent)
    }
}