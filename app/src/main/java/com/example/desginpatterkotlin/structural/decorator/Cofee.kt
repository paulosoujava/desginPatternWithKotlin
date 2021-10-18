package com.example.desginpatterkotlin.structural.decorator

interface CoffeMachine{
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine: CoffeMachine{
    override fun makeSmallCoffee() {
        println("Normal coffee machine: Making small coffee")
    }

    override fun makeLargeCoffee() {
        println("Large coffee machine: Making large coffee")
    }
}

//decorator
class EnhanceCoffeeMachine(private val coffeeMachine: CoffeMachine): CoffeMachine by coffeeMachine{
    //Overriding behaviour
    override fun makeLargeCoffee() {
        println("Enhanced coffee machine: Making large coffee")
    }
    // Extending behaviour
    fun makeMilkCoffee(){
        println("Enhanced coffee machine: Making milk coffee")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced coffee machine: adding milk")
    }
}