package com.example.k2024_02_20_quiz_page.model

class AllQuestions {
    private val allQuestions = arrayListOf<Question<Boolean>>(
        Question<Boolean>("There are 8 days of the week", false, Difficulty.EASY),
        Question<Boolean>("There are 24 hours in a day", true, Difficulty.EASY),
        Question<Boolean>("There are 26 letters in the English alphabet", true, Difficulty.EASY),
        Question<Boolean>("Cats are mammals", true, Difficulty.EASY),
        Question<Boolean>("The capital of France is London", false, Difficulty.EASY),
        Question<Boolean>("Water boils at 100 degrees Fahrenheit", false, Difficulty.EASY)

    )

    fun getNumberOfQuestions() : Int {
        return allQuestions.size
    }

    fun getQuestion(i: Int) : Question<Boolean> {
        return allQuestions[i]
    }

}