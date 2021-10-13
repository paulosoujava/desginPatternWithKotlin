package com.example.desginpatterkotlin.behavioural.iterator

enum class ChannelTypeEnum {
    ENGLISH, HINDI, FRENCH, ALL
}
class Channel(val frequency: Double, val tYPE: ChannelTypeEnum) {

    override fun toString(): String {
        return "Frequency=$frequency, Type=$tYPE"
    }
}
interface ChannelCollection {
    fun addChannel(c: Channel?)
    fun removeChannel(c: Channel?)
    fun iterator(type: ChannelTypeEnum?): ChannelIterator?
}
interface ChannelIterator {
    operator fun hasNext(): Boolean
    operator fun next(): Channel?
}

class ChannelCollectionImpl : ChannelCollection {
    private val channelsList: MutableList<Channel?>
    override fun addChannel(c: Channel?) {
        channelsList.add(c)
    }

    override fun removeChannel(c: Channel?) {
        channelsList.remove(c)
    }

    override fun iterator(type: ChannelTypeEnum?): ChannelIterator? {
        return type?.let { ChannelIteratorImpl(it, channelsList) }
    }

    private inner class ChannelIteratorImpl(
        private val type: ChannelTypeEnum,
        private val channels: MutableList<Channel?>
    ) : ChannelIterator {
        private var position = 0
        override fun hasNext(): Boolean {
            while (position < channels.size) {
                val c = channels[position]
                if (c != null) {
                    if (c.tYPE == type || type == ChannelTypeEnum.ALL) {
                        return true
                    } else position++
                }
            }
            return false
        }

        override fun next(): Channel? {
            val c = channels[position]
            position++
            return c
        }
    }

    init {
        channelsList = ArrayList()
    }
}


    fun main() {
        val channels = populateChannels()
        val baseIterator = channels.iterator(ChannelTypeEnum.ALL)
        while (baseIterator!!.hasNext()) {
            val c = baseIterator.next()
            println(c.toString())
        }
        println("******")
        // Channel Type Iterator
        val englishIterator = channels.iterator(ChannelTypeEnum.ENGLISH)
        while (englishIterator!!.hasNext()) {
            val c = englishIterator.next()
            println(c.toString())
        }
    }

    private fun populateChannels(): ChannelCollection {
        val channels: ChannelCollection = ChannelCollectionImpl()
        channels.addChannel(Channel(98.5, ChannelTypeEnum.ENGLISH))
        channels.addChannel(Channel(99.5, ChannelTypeEnum.HINDI))
        channels.addChannel(Channel(100.5, ChannelTypeEnum.FRENCH))
        channels.addChannel(Channel(101.5, ChannelTypeEnum.ENGLISH))
        channels.addChannel(Channel(102.5, ChannelTypeEnum.HINDI))
        channels.addChannel(Channel(103.5, ChannelTypeEnum.FRENCH))
        channels.addChannel(Channel(104.5, ChannelTypeEnum.ENGLISH))
        channels.addChannel(Channel(105.5, ChannelTypeEnum.HINDI))
        channels.addChannel(Channel(106.5, ChannelTypeEnum.FRENCH))
        return channels
    }
