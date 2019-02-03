package com.github.andrei1993ak.mentoring.koin.network.randomText

data class RandomTextResponse(
    val type: String,
    val amount: Int,
    val number: Int,
    val number_max: Int,
    val time: String,
    val text_out: String
)
