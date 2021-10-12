package com.example.desginpatterkotlin.structural.composite

interface MenuItem {
    fun setName(itemName: String)
    fun getPrice(): Double
    fun printItem()
}
class Meal(val mealPrice: Double) : MenuItem {
    var mealName: String = ""

    override fun setName(itemName: String) {
        mealName = itemName
    }

    override fun getPrice(): Double {
        return mealPrice
    }

    override fun printItem() {
        println("$mealName - $mealPrice")
    }
}
class CompoMeal : MenuItem {
    private var mealName = ""
    private val items = mutableListOf<MenuItem>()

    fun addItem(menuItem: MenuItem) {
        items.add(menuItem)
    }

    fun removeItem(menuItem: MenuItem) {
        items.remove(menuItem)
    }

    override fun setName(itemName: String) {
        mealName = itemName
    }

    override fun getPrice(): Double {
        var total = 0.0
        items.forEach {
            total += it.getPrice()
        }
        return total
    }

    override fun printItem() {
        println("$mealName - ${getPrice()}")
    }
}

fun main() {
    val menuItems = mutableListOf<MenuItem>()

    val burger = Meal(100.0).apply { setName("Burger") }
    val fries = Meal(50.0).apply { setName("Fries") }
    val pizza = Meal(200.0).apply { setName("Pizza") }
    val burgerCombo = CompoMeal().apply { setName("Burger combo meal") }
    val awesomeCombo = CompoMeal().apply { setName("Awesome combo meal") }

    burgerCombo.apply {
        addItem(burger)
        addItem(fries)
    }

    awesomeCombo.apply {
        addItem(burgerCombo)
        addItem(pizza)
    }

    menuItems.addAll(listOf(burger, pizza, burgerCombo, awesomeCombo))

    menuItems.forEach {
        it.printItem()
    }
}