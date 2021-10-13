package com.example.desginpatterkotlin.behavioural.mediator

interface ChatMediator {
    fun sendMessage(msg: String?, user: User?)
    fun addUser(user: User?)
}
abstract class User(protected var mediator: ChatMediator, protected var name: String) {
    abstract fun send(msg: String?)
    abstract fun receive(msg: String?)
}

class ChatMediatorImpl : ChatMediator {
    private val users: MutableList<User?>
    override fun addUser(user: User?) {
        users.add(user)
    }

    override fun sendMessage(msg: String?, user: User?) {
        for (u in users) {
            //message should not be received by the user sending it
            if (u !== user) {
                u!!.receive(msg)
            }
        }
    }

    init {
        users = ArrayList()
    }
}
class UserImpl(med: ChatMediator?, name: String?) :
    User(med!!, name!!) {
    override fun send(msg: String?) {
        println("$name: Sending Message=$msg")
        mediator.sendMessage(msg, this)
    }

    override fun receive(msg: String?) {
        println("$name: Received Message:$msg")
    }
}


    fun main() {
        val mediator: ChatMediator = ChatMediatorImpl()
        val user1: User = UserImpl(mediator, "Pankaj")
        val user2: User = UserImpl(mediator, "Lisa")
        val user3: User = UserImpl(mediator, "Saurabh")
        val user4: User = UserImpl(mediator, "David")
        mediator.addUser(user1)
        mediator.addUser(user2)
        mediator.addUser(user3)
        mediator.addUser(user4)
        user1.send("Hi All")
    }
