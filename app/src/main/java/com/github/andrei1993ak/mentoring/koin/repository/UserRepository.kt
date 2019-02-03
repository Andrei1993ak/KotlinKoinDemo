package com.github.andrei1993ak.mentoring.koin.repository

import io.reactivex.Single

interface UserRepository {
    fun getUsers(): Single<List<User>>
    fun removeUser(userId: Long): Single<Boolean>
}