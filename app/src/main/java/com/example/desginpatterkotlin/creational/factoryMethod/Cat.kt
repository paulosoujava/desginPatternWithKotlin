package com.example.desginpatterkotlin.creational.factoryMethod

interface Animal{
    val name : String
}

class Cat : Animal{
    override val name = "CAT"
}
class Dog : Animal{
    override val name = "DOG"
}
class Bird : Animal{
    override val name = "BIRD"
}
class UnknownAnimal : Animal{
    override val name = "Unknown Animal"
}

fun animalFactory(animalType: String) : Animal {
    return when(animalType.lowercase()){
        "cat" -> Cat()
        "bird" -> Bird()
        "dog" -> Dog()
        else ->
           UnknownAnimal()
    }
}

fun main() {
    val animalTypes = listOf("dog", "cat", "bird", "other")
    for(n in animalTypes){
        val c = animalFactory(n)
        print(c.name+"\n")
    }

}