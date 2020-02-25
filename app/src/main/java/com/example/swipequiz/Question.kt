package com.example.swipequiz

data class Question(
    var theQuestion: String,
    var answerIndex: Int
) {
    public var answer: Boolean = false
}
