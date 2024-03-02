package com.example.k2024_02_20_quiz_page.model

data class Question<T> (
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)