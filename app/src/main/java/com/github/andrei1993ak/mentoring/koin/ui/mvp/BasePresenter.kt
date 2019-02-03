package com.github.andrei1993ak.mentoring.koin.ui.mvp

interface BasePresenter<T> {

    fun stop()

    var view: T
}