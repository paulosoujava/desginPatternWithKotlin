package com.example.desginpatterkotlin.creational.builder
//no kotlin fica facil criar este design pattern
// pois vc pode passar já os parametros por defaul,
//voc pode usar o auxiliares de contexto apply
//sende desnecessário o DIRETOR
data class House(
    var hasRoof: Boolean? = false,
    var hasGarage: Boolean? = false,
    var hasWindow: Boolean? = false,
    val hasManyDoor: Int,
    val hasManyBathroom: Int
){
    override fun toString(): String {
        return "House(hasRoof=$hasRoof, hasGarage=$hasGarage, hasWindow=$hasWindow, hasManyDoor=$hasManyDoor, hasManyBathroom=$hasManyBathroom)"
    }
}

fun main() {
    val buildHouseWithGarage = House(
        hasManyDoor = 2,
        hasManyBathroom = 2).apply {
        hasGarage = true
        hasRoof = true
    }
    // OR
    val buildHouseWithGarage2 = House(
        hasManyDoor = 2,
        hasManyBathroom = 2,
        hasRoof = true,
        hasGarage = true)

    println(buildHouseWithGarage)
    println(buildHouseWithGarage2)
}

