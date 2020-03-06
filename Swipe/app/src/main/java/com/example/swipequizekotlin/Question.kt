package com.example.swipequizekotlin

/**
 * Dataclass. Voor het displayen van data
 * Comnpion object is gewoon een statisch object met de data
 */
data class Question(
    val quizQuestion: String,
    val questionAwnser: Boolean
) {
    companion object {
        val QUESTIONS_FOR_QUIZE = arrayOf(
            "Web development is better than mobile development",
            "Mobile Application Development grants 12 ETCS",
            "A unit in kotlin corresponds to a void in Java",
            "In kotlin 'when' replaces the 'switch' operator in Java"
        )
        val QUESTION_AWNSERS_FOR_QUIZE = arrayOf(
            true,
            true,
            false,
            true
        )
    }
}