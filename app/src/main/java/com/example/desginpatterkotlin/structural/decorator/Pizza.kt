package com.example.desginpatterkotlin.structural.decorator
interface Taste{
    fun getTaste()
    fun size()
}

class Pizza: Taste{
    override fun getTaste() {
        println("It's a PIZZA")
    }

    override fun size() {
        println("It's a BIG PIZZA")
    }
}
open class PizzaDecorator(private var pizza: Pizza): Taste{
    override fun getTaste() {
        this.pizza.getTaste()
    }
    override fun size() {
        this.pizza.size()
    }
}
class CatuPeru( p: Pizza): PizzaDecorator(p){
    override fun getTaste() {
        super.getTaste()
        this.addStuff()
        println("A  CATUPERU PIZZA ")
    }

    fun addStuff(){
        println("ADD MORE STUFFS")
    }
}
class Banana( p: Pizza): PizzaDecorator(p){
    override fun getTaste() {
        super.getTaste()
        this.addBanana()
        println("A BANNA PIZZA ")
    }
    fun addBanana(){
        println("ADD MORE BANANAS ")
    }
}

fun main() {
    val p1 = CatuPeru(Pizza())
    println(p1.getTaste())
    println(Banana(Pizza()).getTaste())
}