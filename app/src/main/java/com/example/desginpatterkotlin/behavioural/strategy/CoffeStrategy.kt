package com.example.desginpatterkotlin.behavioural.strategy

abstract class CoffeeStrategy {
    abstract  fun announce(roast: String): String
}
class DripStrategy : CoffeeStrategy(){
    override fun announce(roast: String) = "a drip coffee with $roast beans";
}

class AmericanoStrategy : CoffeeStrategy(){
    override fun announce(roast: String) = "an Americano with $roast beans"
}
class MochaFrappuccinoStrategy : CoffeeStrategy() {
    override fun announce(roast: String) = "a delicious mocha frappuccion with $roast beans";
}
class CoffeeDrinker( var name: String, val preferredDrink: CoffeeStrategy)

fun main() {
    var americano = AmericanoStrategy();
    var drip = DripStrategy();
    var mocha = MochaFrappuccinoStrategy();

    var me = CoffeeDrinker("Tyler", drip);
    var europeanBuddy = CoffeeDrinker("Pieter", americano);
    var myDaughter = CoffeeDrinker("Joanie", mocha);
    val listCoffee = listOf(me, europeanBuddy, myDaughter)

    var roastOfTheDay = "Italian";

    for ( person in listCoffee) {
        print("Hey ${person.name}, whatcha drinkin' over there?");
        print("I'm enjoying ${person.preferredDrink.announce(roastOfTheDay)}!\r\n");
    }
}