package com.example.desginpatterkotlin.behavioural

class Memento(val state: String)

class Originator {

    //this String is just for example
    //in real world application this
    //will be the object for which the state to be stored
    var state: String? = null

    fun createMemento(): Memento {
        return Memento(state!!)
    }

    fun setMemento(memento: Memento) {
        state = memento.state
    }
}

class Caretaker {
    private val statesList = ArrayList<Memento>()

    fun addMemento(m: Memento) {
        statesList.add(m)
    }

    fun getMemento(index: Int): Memento {
        return statesList.get(index)
    }
}

fun main() {
    val originator = Originator()
    originator.state = "Ironman"
    var memento = originator.createMemento()
    val caretaker = Caretaker()
    caretaker.addMemento(memento)

    originator.state = "Captain America"
    originator.state = "Hulk"
    memento = originator.createMemento()
    caretaker.addMemento(memento)
    originator.state = "Thor"
    println("Originator Current State: " + originator.state!!)
    println("Originator restoring to previous state...")
    memento = caretaker.getMemento(1)
    originator.setMemento(memento)
    println("Originator Current State: " + originator.state!!)
    println("Again restoring to previous state...")
    memento = caretaker.getMemento(0)
    originator.setMemento(memento)
    println("Originator Current State: " + originator.state!!)
}