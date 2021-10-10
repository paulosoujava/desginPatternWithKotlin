package com.example.desginpatterkotlin.factoryMethod

fun main() {

    val animalType2 = listOf(
        AnimalType.DOG to AnimalBreed.PITBULL,
        AnimalType.DOG to AnimalBreed.BULDOG,
        AnimalType.CAT to AnimalBreed.ANGORA,
        AnimalType.CAT to AnimalBreed.PERSA,
        AnimalType.BIRD to AnimalBreed.CANARY,
        AnimalType.BIRD to AnimalBreed.PARROT,
    )
    var count =0;
    for ((type, breed) in animalType2) {

        val c = animalFactory2(type, breed, ++count)
        print("${c.id} - ${c.name}\n")
    }
}
