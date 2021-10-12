package com.example.desginpatterkotlin.creational.prototype

data class PC(val motherboard: String = "Terasus XZ27",
              val cpu: String = "Until Atom K500",
              val ram: String = "8GB Microcend BBR5",
              val graphicCard: String = "nKCF 8100TZ"){
    override fun toString(): String {
        return "PC(motherboard='$motherboard', cpu='$cpu', ram='$ram', graphicCard='$graphicCard')"
    }
}


fun main() {
    val pc = PC()
    println(pc)
    val otherPcWithSameConfiguration = pc.copy()
    println(otherPcWithSameConfiguration)
    val otherPcWithLessConfiguration = pc.copy(ram = "4GB Microcend BBR5")
    println(otherPcWithLessConfiguration)
}