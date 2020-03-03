package com.example.swipequizekotlin

data class Question(
    val QuizQuestion: String,
    val Questionawnser: Boolean
) {
    companion object {
        val QUESTIONS_FOR_QUIZE = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grants 12 ETCS",
            "A unit in kotlin corresponds to a void in Java",
            "In kotlin 'when' replaces the 'switch' operator in Java"
        )
        val QUESTION_AWNSERS_FOR_QUIZE = arrayOf(
            false,
            true,
            false,
            true
        )
    }
}