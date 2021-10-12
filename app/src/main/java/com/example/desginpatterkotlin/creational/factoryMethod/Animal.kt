package com.example.desginpatterkotlin.creational.factoryMethod

enum class AnimalType {
    DOG,
    CAT,
    BIRD,
}
enum class AnimalBreed{
    BULDOG,
    PITBULL,
    PARROT,
    CANARY,
    ANGORA,
    PERSA
}


interface Animal2 {
    val id: Int
    val name: String
}

private class UnknownAnimal2(count: Int) : Animal2 {
    override val name = "Unknown Animal"
    override val id = count
}

// ANIMAL DOG
private class DogFactory {
    fun create(breed: AnimalBreed, count: Int): Animal2 {
        return when (breed) {
            AnimalBreed.PITBULL -> Pitbull(count)
            AnimalBreed.BULDOG -> Buldog(count)
            else ->UnknownAnimal2(count)
        }
    }

}
private class Pitbull(count: Int) : Animal2 {
    override val name = "PITBULL"
    override val id = count
}
private class Buldog(count: Int) : Animal2 {
    override val name = "buldog"
    override val id = count
}

//ANIMAL CAT
private class CatFactory{
    fun create(breed: AnimalBreed, count: Int): Animal2{
        return when (breed) {
            AnimalBreed.ANGORA -> Angora(count)
            AnimalBreed.PERSA -> Persa(count)
            else -> UnknownAnimal2(count)
        }

    }
}
private class Angora(count: Int) : Animal2 {
    override val name = "ANGORA"
    override val id = count
}
private class Persa(count: Int) : Animal2 {
    override val name = "Persa"
    override val id = count
}

//ANIMAL BIRD
private class BirdFactory{
    companion object{
        fun create(breed:AnimalBreed, count: Int): Animal2{
            return when (breed) {
                AnimalBreed.PARROT -> Parrot(count)
                AnimalBreed.CANARY -> Canary(count)
                else -> UnknownAnimal2(count)
            }
        }
    }

}
private class Parrot(count: Int) : Animal2{
    override val name = "PAPAGAIO"
    override val id = count
}
private class Canary(count: Int) : Animal2{
    override val name = "Canarinho"
    override val id = count
}


fun animalFactory2(animalType: AnimalType, animalBreed: AnimalBreed, counter: Int): Animal2 {

    val dogFactory = DogFactory()
    val catFactory = CatFactory()

    return when (animalType) {
        AnimalType.DOG -> dogFactory.create(animalBreed, counter)
        AnimalType.CAT -> catFactory.create(animalBreed, counter)
        AnimalType.BIRD-> BirdFactory.create(animalBreed, counter)
    }
}


