package com.github.andrei1993ak.mentoring.koin.ui.list

import android.content.Context
import com.github.andrei1993ak.mentoring.koin.navigation.AppNavigator
import com.github.andrei1993ak.mentoring.koin.R
import com.github.andrei1993ak.mentoring.koin.repository.User
import com.github.andrei1993ak.mentoring.koin.repository.UserRepository
import com.github.andrei1993ak.mentoring.koin.ui.mvp.AbstractPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserListPresenter(
    private val context: Context,
    private val repository: UserRepository,
    private val appNavigator: AppNavigator,
    override var view: ListContract.View
) : AbstractPresenter<ListContract.View, ListContract.Presenter>(), ListContract.Presenter {

    override fun getList() {
        loadUsers()
    }

    override fun onUserItemClicked(user: User) {
        appNavigator.goToDetailPage(user, context)
    }

    override fun onIemSwipedToDelete(userId: Long) {
        view.showLoading()

        launch {
            repository.removeUser(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { success ->
                        view.hideLoading()

                        if (success) {
                            view.removeUser(userId)
                        } else {
                            view.showError(context.getString(R.string.unknown_error))
                        }
                    },
                    { view.showError(context.getString(R.string.unknown_error)) }
                )
        }
    }

    private fun loadUsers() {
        view.showLoading()

        launch {
            repository.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { usersList ->
                        view.hideLoading()
                        view.updateUsers(usersList)
                    },
                    { view.showError(context.getString(R.string.unknown_error)) }
                )
        }
    }
}