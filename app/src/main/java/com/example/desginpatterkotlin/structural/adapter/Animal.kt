package com.example.desginpatterkotlin.structural.adapter

interface Speak {
    fun speak()
}

interface Meowable {
    fun meow()
}

interface Barkable {
    fun bark()
}

open class Cat : Meowable {
    val name = "MAGALI"
    override fun meow() {
        println("miauuuu")
    }

}

class Dog : Barkable {
    val name = "BILU"
    override fun bark() {
        println("AU AU")
    }
}

class DogAdapter(private val dog: Dog) : Speak {
    override fun speak() {
        println("${dog.name} late estilo gato: miauuuu")
    }
}
class CatAdapter(private val cat: Cat) : Speak {
    override fun speak() {
        println("${cat.name} mia estilo cachorro: au au au")
    }
}

fun main() {
    DogAdapter(Dog()).speak()
    CatAdapter(Cat()).speak()
}