package com.github.andrei1993ak.mentoring.koin.di

import com.github.andrei1993ak.mentoring.koin.navigation.AppNavigatorImpl
import com.github.andrei1993ak.mentoring.koin.navigation.AppNavigator
import com.github.andrei1993ak.mentoring.koin.repository.UserRepository
import com.github.andrei1993ak.mentoring.koin.repository.UserRepositoryImpl
import com.github.andrei1993ak.mentoring.koin.ui.details.DetailContract
import com.github.andrei1993ak.mentoring.koin.ui.details.UserDetailsPresenter
import com.github.andrei1993ak.mentoring.koin.ui.list.ListContract
import com.github.andrei1993ak.mentoring.koin.ui.list.UserListPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appNavigatorModule = module {
    single { AppNavigatorImpl() as AppNavigator }
}

val userRepositoryModule = module {
    single { UserRepositoryImpl(get(), get()) as UserRepository }
}

val appModule = module {
    factory  { (view: DetailContract.View) -> UserDetailsPresenter(view) as DetailContract.Presenter }
    factory  { (view: ListContract.View) ->
        UserListPresenter(
            androidContext(),
            get(),
            get(),
            view
        ) as ListContract.Presenter
    }
}
