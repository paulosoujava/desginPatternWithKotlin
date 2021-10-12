package com.example.desginpatterkotlin.creational.abstractFactory

interface Animal{
    val name: String
}

interface Dog : Animal{
    fun size():String
}
class SmallDog(override val name: String) : Dog{
    override fun size()= "SMALL DOG"
}
class MediumDog(override val name: String) : Dog{
    override fun size()= "MEDIUM DOG"
}
class BigDog(override val name: String) : Dog{
    override fun size()= "BIG DOG"
}

interface Cat : Animal{
    fun size():String
}
class SmallCat(override val name: String) : Cat{
    override fun size()= "SMALL Cat"
}
class MediumCat(override val name: String) : Cat{
    override fun size()= "MEDIUM Cat"
}
class BigCat(override val name: String) : Cat{
    override fun size()= "BIG Cat"
}

interface Bird : Animal{
    fun size():String
}
class SmallBird(override val name: String) : Bird{
    override fun size()= "SMALL Bird"
}
class MediumBird(override val name: String) : Bird{
    override fun size()= "MEDIUM Bird"
}
class BigBird(override val name: String) : Bird{
    override fun size()= "BIG Bird"
}



interface FurnitureAnimal{
    fun createDog(): Dog
    fun createCat(): Cat
    fun createBird(): Bird
}
class SmallFurnitureFactory : FurnitureAnimal{
    override fun createDog() = SmallDog("SMALL DOG")
    override fun createCat()= SmallCat("SMALL CAT")
    override fun createBird()= SmallBird("SMALL BIRD")

}
class MediumFurnitureFactory : FurnitureAnimal{
    override fun createDog() = MediumDog("Medium DOG")
    override fun createCat()= MediumCat("Medium CAT")
    override fun createBird()= MediumBird("Medium BIRD")

}
class BigFurnitureFactory : FurnitureAnimal{
    override fun createDog() = BigDog("Big DOG")
    override fun createCat()= BigCat("Big CAT")
    override fun createBird()= BigBird("Big BIRD")

}


fun main() {
    val small = SmallFurnitureFactory()
    println(small.createDog().name)
    println(small.createCat().name)
    println(small.createBird().name)
    println()
    val medium = MediumFurnitureFactory()
    println(medium.createDog().name)
    println(medium.createCat().name)
    println(medium.createBird().name)
    println()
    val big = BigFurnitureFactory()
    println(big.createDog().name)
    println(big.createCat().name)
    println(big.createBird().name)
}