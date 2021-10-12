package com.example.desginpatterkotlin.creational.abstractFactory

interface Chair{
    fun hasLeg():String
}
class VictorianChair: Chair{
    override fun hasLeg() = "MODEL VICTORIAN WITH LEG"
}
class ModernChair: Chair{
    override fun hasLeg() = "MODEL VICTORIAN WITHOUT LEG"

}

interface Table{
    fun hasSeat():String
}
class VictorianTable: Table{
    override fun hasSeat() = "MODEL VICTORIAN WITH hasSeat"
}
class ModernTable: Table{
    override fun hasSeat() = "MODEL VICTORIAN WITHOUT hasSeat"

}

interface Sofa{
    fun hasBig():String
}
class VictorianSofa: Sofa{
    override fun hasBig() = "MODEL VICTORIAN hasBigt"
}
class ModernSofa: Sofa{
    override fun hasBig() = "MODEL VICTORIAN NOT hasBig"

}


interface Furniture{
    fun createChair(): Chair
    fun createTable(): Table
    fun createSofa(): Sofa
}
class VictorianFurnitureFactory : Furniture{
    override fun createChair() = VictorianChair()
    override fun createTable()= VictorianTable()
    override fun createSofa()= VictorianSofa()

}
class ModernFurnitureFactory : Furniture{
    override fun createChair() = ModernChair()
    override fun createTable() = ModernTable()
    override fun createSofa() = ModernSofa()

}

fun main() {
    val modern = ModernFurnitureFactory()
    println(modern.createChair().hasLeg())
    println(modern.createTable().hasSeat())
    println(modern.createSofa().hasBig())
println()
    val victory = VictorianFurnitureFactory()
    println(victory.createChair().hasLeg())
    println(victory.createTable().hasSeat())
    println(victory.createSofa().hasBig())
}