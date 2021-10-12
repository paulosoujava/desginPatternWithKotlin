package com.example.desginpatterkotlin.behavior.chainOfResponsability

import java.util.*

interface DispenseChain{
    fun setNextChain(nextChain: DispenseChain?)
    fun dispense(cur: Currency)
}

class Currency(val amount: Int){}

//handler 1
class Dollar50Dispenser : DispenseChain {
    private var chain: DispenseChain? = null
    override fun setNextChain(nextChain: DispenseChain?) {
        chain = nextChain
    }

    override fun dispense(cur: Currency) {
        if (cur.amount >= 50) {
            val num = cur.amount / 50
            val remainder = cur.amount % 50
            println("Dispensing $num 50$ note")
            if (remainder != 0) chain!!.dispense(Currency(remainder))
        } else {
            chain!!.dispense(cur)
        }
    }
}

//handler 2
class Dollar20Dispenser : DispenseChain {
    private var chain: DispenseChain? = null
    override fun setNextChain(nextChain: DispenseChain?) {
        chain = nextChain
    }

    override fun dispense(cur: Currency) {
        if (cur.amount >= 20) {
            val num = cur.amount / 20
            val remainder = cur.amount % 20
            println("Dispensing $num 20$ note")
            if (remainder != 0) chain!!.dispense(Currency(remainder))
        } else {
            chain!!.dispense(cur)
        }
    }
}
//handler 3
class Dollar10Dispenser : DispenseChain {
    private var chain: DispenseChain? = null
    override fun setNextChain(nextChain: DispenseChain?) {
        chain = nextChain
    }

    override fun dispense(cur: Currency) {
        if (cur.amount >= 10) {
            val num = cur.amount / 10
            val remainder = cur.amount % 10
            println("Dispensing $num 10$ note")
            if (remainder != 0) chain!!.dispense(Currency(remainder))
        } else {
            chain!!.dispense(cur)
        }
    }
}

class ATMDispenseChain {
    private val c1: DispenseChain

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val atmDispenser = ATMDispenseChain()
            while (true) {
                var amount = 0
                println("Enter amount to dispense")
                val input = Scanner(System.`in`)
                amount = input.nextInt()
                if (amount % 10 != 0) {
                    println("Amount should be in multiple of 10s.")
                    return
                }
                // process the request
                atmDispenser.c1.dispense(Currency(amount))
            }
        }
    }

    init {
        // initialize the chain
        c1 = Dollar50Dispenser()
        val c2: DispenseChain = Dollar20Dispenser()
        val c3: DispenseChain = Dollar10Dispenser()

        // set the chain of responsibility
        c1.setNextChain(c2)
        c2.setNextChain(c3)
    }
}



