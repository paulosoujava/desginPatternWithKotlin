package com.example.desginpatterkotlin.structural.bridge

interface Device {
    fun isEnabled(): Boolean
    fun enabled()
    fun disabled()
    fun getVolume(): Int
    fun setVolume(percent: Int)
    fun getChannel(): Int
    fun setChannel(channel: Int)
    fun printStatus()
}

class Radio : Device {
    private var on = true
    private var volume = 30
    private var channel = 1

    override fun isEnabled() = on

    override fun enabled() {
        on = true
    }

    override fun disabled() {
        on = false
    }

    override fun getVolume() = volume

    override fun setVolume(percent: Int) {
        if (volume > 100) {
            this.volume = 100
        } else if (volume < 0) {
            this.volume = 0
        } else {
            this.volume = volume
        }
    }

    override fun getChannel() = channel

    override fun setChannel(channel: Int) {
        this.channel = channel
    }

    override fun printStatus() {
        println("------------------------------------")
        println("| I'm radio.")
        println("| I'm " + if (on) "enabled" else "disabled")
        println("| Current volume is $volume%")
        println("| Current channel is $channel")
        println("------------------------------------\n")
    }

}

class Tv : Device {
    private var on = false
    private var volume = 30
    private var channel = 1

    override fun isEnabled() = on

    override fun enabled() {
        on = true
    }

    override fun disabled() {
        on = false
    }

    override fun getVolume(): Int {
        return volume
    }

    override fun setVolume(volume: Int) {
        if (volume > 100) {
            this.volume = 100
        } else if (volume < 0) {
            this.volume = 0
        } else {
            this.volume = volume
        }
    }

    override fun getChannel(): Int {
        return channel
    }

    override fun setChannel(channel: Int) {
        this.channel = channel
    }

    override fun printStatus() {
        println("------------------------------------")
        println("| I'm TV set.")
        println("| I'm " + if (on) "enabled" else "disabled")
        println("| Current volume is $volume%")
        println("| Current channel is $channel")
        println("------------------------------------\n")
    }
}

interface Remote {
    fun power()
    fun volumeDown()
    fun volumeUp()
    fun channelDown()
    fun channelUp()
}

open class BasicRemote(private val device: Device) : Remote {

    override fun power() {
        println("Remote: power toggle")
        if (device.isEnabled()) {
            device.disabled()
        } else {
            device.enabled()
        }
    }

    override fun volumeDown() {
        println("Remote: volume down")
        device.setVolume(device.getVolume() - 10)
    }

    override fun volumeUp() {
        println("Remote: volume up")
        device.setVolume(device.getVolume() + 10)
    }

    override fun channelDown() {
        println("Remote: channel down")
        device.setChannel(device.getChannel() - 1)
    }

    override fun channelUp() {
        println("Remote: channel up")
        device.setChannel(device.getChannel() + 1)
    }
}

class AdvancedRemote(private val device: Device) : BasicRemote(device) {

    fun mute() {
        println("Remote: mute")
        device.setVolume(0)
    }

}

fun main() {
    test(Tv())
    test(Radio())

}
fun test(device: Device){
    println("Tests with basic remote.")
    val basicRemote = BasicRemote(device)
    basicRemote.power()
    device.printStatus()

    println("Tests with advanced remote.")
    val advancedRemote = AdvancedRemote(device)
    advancedRemote.power()
    advancedRemote.mute()
    device.printStatus()
}