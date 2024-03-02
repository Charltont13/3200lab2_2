package com.example.k2024_02_20_quiz_page.controller

class NextQuestion(private val totalQuestions: Int) {

    companion object {
        private var currentQuestion = 0
    }

    fun getQuestionNumber() : Int {
        return currentQuestion
    }
    private fun incQuestionNumber() {
        currentQuestion = (currentQuestion +1) % totalQuestions
    }
    fun getNextIncQuestionNumber() : Int {
        incQuestionNumber()
        return currentQuestion
    }

    fun getNextRandomQuestionNumber(): Int {
        return (0..totalQuestions).random()
    }
}