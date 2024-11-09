package com.puc.quiz.data.quiz

data class Question(
    val imageUrl: String,
    val answer: String,
    var options: List<String>
)
