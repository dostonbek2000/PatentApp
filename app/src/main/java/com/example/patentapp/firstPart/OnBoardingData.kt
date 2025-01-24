package com.example.patentapp.firstPart

import com.example.patentapp.R

class OnBoardingData(val image: Int, val title: String, val desc: String)

val listData = listOf(
    OnBoardingData(
        R.drawable.a,
        "Добро пожаловать!",
        "Легко и быстро находите ответы на вопросы, где бы вы ни находились. Наше приложение создано, чтобы помочь вам справляться с любыми ситуациями за границей."
    ),
    OnBoardingData(
        R.drawable.b,
        "Ваш помощник за границей",
        "Выбирайте нужный вариант, отвечайте на вопросы и получайте ценные результаты. Всё просто, удобно и понятно."
    ),
    OnBoardingData(
        R.drawable.c,
        "Для ваших успехов",
        "Учитесь, практикуйтесь и улучшайте свои знания с каждым днём. Мы здесь, чтобы поддержать вас на каждом шагу."
    )
)