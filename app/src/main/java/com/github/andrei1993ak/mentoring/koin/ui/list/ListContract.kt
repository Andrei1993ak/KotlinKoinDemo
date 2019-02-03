package com.github.andrei1993ak.mentoring.koin.ui.list

import com.github.andrei1993ak.mentoring.koin.repository.User
import com.github.andrei1993ak.mentoring.koin.ui.mvp.BasePresenter
import com.github.andrei1993ak.mentoring.koin.ui.mvp.BaseView

interface ListContract {
    interface View : BaseView<Presenter> {
        fun updateUsers(users: List<User>)

        fun showError(error: String)

        fun showLoading()

        fun removeUser(userId: Long)

        fun hideLoading()
    }

    interface Presenter : BasePresenter<View> {
        fun onIemSwipedToDelete(userId: Long)
        fun getList()
        fun onUserItemClicked(user: User)
    }
}