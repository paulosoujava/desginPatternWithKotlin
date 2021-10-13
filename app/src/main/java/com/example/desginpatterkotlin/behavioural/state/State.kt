package com.example.desginpatterkotlin.behavioural.state

interface MobileAlertState {
    fun alert(ctx: AlertStateContext)
}

class AlertStateContext {
    private var currentState: MobileAlertState? = null

    init {
        currentState = Vibration()
    }

    fun setState(state: MobileAlertState) {
        currentState = state
    }

    fun alert() {
        currentState!!.alert(this)
    }
}

class Vibration : MobileAlertState {

    override fun alert(ctx: AlertStateContext) {
        println("vibration...")
    }
}

class Silent : MobileAlertState {

    override fun alert(ctx: AlertStateContext) {
        println("silent...")
    }
}

class Sound : MobileAlertState {

    override fun alert(ctx: AlertStateContext) {
        println("tu..tu..tu..tu")
    }
}

fun main() {
    val stateContext = AlertStateContext()
    stateContext.alert()
    stateContext.alert()
    stateContext.setState(Silent())
    stateContext.alert()
    stateContext.alert()
    stateContext.setState(Sound())
    stateContext.alert()
}