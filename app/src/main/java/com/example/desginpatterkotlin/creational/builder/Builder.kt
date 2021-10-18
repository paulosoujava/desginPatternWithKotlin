package com.example.desginpatterkotlin.creational.builder

data class Mail(
    val to: String,
    var title: String = "",
    var _message: String = "",
    var cc: List<String> = listOf(),
    val bcc: List<String> = listOf(),
    val attachments: List<java.io.File> = listOf()
) {

    fun message(message: String) = apply {
        _message = message
    }
    fun title(title: String) = apply {
        this.title = title
    }

}

fun main() {

    println(Mail("hello").message("How are you?").apply {
        title = "this is my title"
    })

    println(Mail("helloAgain").apply {
        message("My message")
        title = "This is my title"
    })


    println(Mail("LastTest")
        .message("Last message")
        .title("My title"))

}