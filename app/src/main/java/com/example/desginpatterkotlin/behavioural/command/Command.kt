package com.example.desginpatterkotlin.behavioural.command


interface Command {
    fun execute()
}

class OnCommand(private val ce: ConsumerElectronics) : Command {
    override fun execute() {
        ce.on()
    }
}

class MuteAllCommand(private var ceList: List<ConsumerElectronics>) : Command {

    override fun execute() {
        for (ce in ceList) {
            ce.mute()
        }
    }
}

interface ConsumerElectronics {
    fun on()
    fun mute()
}

class Television : ConsumerElectronics {

    override fun on() {
        println("Television is on!")
    }

    override fun mute() {
        println("Television is muted!")
    }
}

class SoundSystem : ConsumerElectronics {

    override fun on() {
        println("Sound system is on!")
    }

    override fun mute() {
        println("Sound system is muted!")
    }
}

class Button(var c: Command) {
    fun click() {
        c.execute()
    }
}

class UniversalRemote {
    // here we will have a complex electronic circuit :-)
    // that will maintain current device
    object active {
          fun device(): ConsumerElectronics {
            return Television()
        }
    }

}

fun main() {

    // OnCommand is instantiated based on active device supplied by Remote
    val ce = UniversalRemote.active.device()
    val onCommand = OnCommand(ce)
    val onButton = Button(onCommand)
    onButton.click()

    val tv = Television()
    val ss = SoundSystem()
    val all = ArrayList<ConsumerElectronics>()
    all.add(tv)
    all.add(ss)
    val muteAll = MuteAllCommand(all)
    val muteAllButton = Button(muteAll)
    muteAllButton.click()
}