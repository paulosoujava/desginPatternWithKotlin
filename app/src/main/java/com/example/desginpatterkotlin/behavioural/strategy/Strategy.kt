package com.example.desginpatterkotlin.behavioural.strategy

class Printer(val strategy: (String) -> String) {
    fun print(string: String): String = strategy(string)
}
val upperCaseFormatter: (String) -> String = String::uppercase
val lowerCaseFormatter: (String) -> String = String::lowercase
fun main() {
    val lower = Printer(strategy = lowerCaseFormatter)
    println(lower.print("O tempora, o mores!"))
    val upper = Printer(strategy = upperCaseFormatter)
    println(upper.print("O tempora, o mores!"))
}