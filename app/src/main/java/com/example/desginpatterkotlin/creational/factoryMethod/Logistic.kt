package com.example.desginpatterkotlin.creational.factoryMethod

import java.lang.RuntimeException

interface Transport{
    fun deliver():String
    fun price():String
}
 abstract class Logistic: Transport{
    abstract fun planDelivery():String
     abstract fun createTransport():String
}

class Ship {
    val name = "SHIP"
}
class Truck {
    val name = "TRUCK"
}

open class RoadLogistic: Logistic(){
    override fun createTransport(): String {
        val truk = Truck()
        return "BY ${truk.name}"
    }
    override fun deliver() = "DELIVER FOR ROAD "
    override fun price()= "PRICE FOR ROAD"
    override fun planDelivery() = "LOGISTIC FOR ROAD"
}
open class SeaLogistic: Logistic(){
    override fun createTransport(): String {
        val ship = Ship()
        return "BY ${ship.name} "
    }
    override fun deliver() = "DELIVER FOR SHIP "
    override fun price()= "PRICE FOR SHIP"
    override fun planDelivery() = "LOGISTIC FOR SHIP"
}

fun logisticFactory(typeLogistic: String): Logistic{
    return when(typeLogistic){
        "truck" -> RoadLogistic()
        "ship" -> SeaLogistic()
        else ->
            throw RuntimeException("Unknown Logistic")
    }
}

fun main() {
    val logistic = listOf("truck", "ship")
    for(l in logistic){
        val ll =  logisticFactory(l)
        println(ll.createTransport())
        println(ll.planDelivery())
        println(ll.deliver())
        println(ll.price())
        println()
    }

}
