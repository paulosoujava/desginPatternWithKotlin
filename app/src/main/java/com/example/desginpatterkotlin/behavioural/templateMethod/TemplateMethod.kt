package com.example.desginpatterkotlin.behavioural.templateMethod

sealed class Drink {
    fun prepareDrink() {
        println("prepare ${this.javaClass.simpleName} ...")

        boilWater()
        addIngredient()
        fillWaterInCup()

        println("... finished\n")
    }

    /** template method */
    protected abstract fun addIngredient()

    private fun boilWater() = println("boil water")
    private fun fillWaterInCup() = println("fill cup with hot water")
}

object Coffee : Drink() {
    override fun addIngredient() = println("insert coffee powder")
}

object Tea : Drink() {
    override fun addIngredient() = println("insert tea leaves")
}


fun main() {
    Coffee.prepareDrink()
    Tea.prepareDrink()
}