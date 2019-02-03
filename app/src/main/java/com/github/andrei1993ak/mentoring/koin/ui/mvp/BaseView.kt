package com.github.andrei1993ak.mentoring.koin.ui.mvp

interface BaseView<out T : BasePresenter<*>> {

    val presenter: T

}