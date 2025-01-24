package com.example.patentapp.data

data class MainData(
    val requirement: String? = null,
    val text: String? = null,
    val audio: Int? = null,
    val question: String? = null,
    val type: String? = null,
    val answers: List<String>? = null,
    val anketa:String?=null,
    val answersB: List<Int>? = null,
    val correctAnswer: String? = null,
    val correctB: Int? = null
)