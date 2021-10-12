package com.example.desginpatterkotlin.structural.composite

abstract class IceCream {
    abstract fun getDescription(): String
}

class VanillaIceCream : IceCream() {
    override fun getDescription() = "Vanilla ice cream"
}

class StrawberryIceCream : IceCream() {
    override fun getDescription() = "Strawberry ice cream"
}

class ChocolateIceCream : IceCream() {
    override fun getDescription() = "Chocolate ice cream"
}

abstract class ToppingDecorator() : IceCream() {
    abstract fun addTopping(): String
}

class HoneyToppingDecorator(private val iceCream: IceCream) : ToppingDecorator() {
    override fun getDescription() = iceCream.getDescription() + addTopping()
    override fun addTopping() = " With honey topping &"
}

class NutsToppingDecorator(private val iceCream: IceCream) : ToppingDecorator() {
    override fun getDescription() = iceCream.getDescription() + addTopping()
    override fun addTopping() = " With nuts topping &"
}

fun main() {
    val vanillaIceCreamWithHoneyTopping = HoneyToppingDecorator(VanillaIceCream())
    val vanillaIceCreamWithHoneyToppingDescription = vanillaIceCreamWithHoneyTopping.getDescription()
    println("You ordered: ${vanillaIceCreamWithHoneyToppingDescription.trimEnd('&')}")

    val strawberryCreamWithNutsTopping = NutsToppingDecorator(StrawberryIceCream())
    val strawberryCreamWithNutsToppingDescription = strawberryCreamWithNutsTopping.getDescription()
    println("You ordered: ${strawberryCreamWithNutsToppingDescription.trimEnd('&')}")

    val chocolateIceCreamWithHoneyAndNutsTopping = NutsToppingDecorator(HoneyToppingDecorator(ChocolateIceCream()))
    val chocolateIceCreamWithHoneyAndNutsToppingDescription = chocolateIceCreamWithHoneyAndNutsTopping.getDescription()
    println("You ordered: ${chocolateIceCreamWithHoneyAndNutsToppingDescription.trimEnd('&')}")
}