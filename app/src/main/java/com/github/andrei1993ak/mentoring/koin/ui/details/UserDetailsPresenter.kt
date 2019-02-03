package com.github.andrei1993ak.mentoring.koin.ui.details

import android.content.Intent
import com.github.andrei1993ak.mentoring.koin.utils.USER_EXTRA_KEY
import com.github.andrei1993ak.mentoring.koin.ui.mvp.AbstractPresenter

class UserDetailsPresenter(override var view: DetailContract.View) :
    AbstractPresenter<DetailContract.View, DetailContract.Presenter>(), DetailContract.Presenter {
    override fun getDetail(intent: Intent) {
        view.displayUser(intent.getParcelableExtra(USER_EXTRA_KEY))
    }
}