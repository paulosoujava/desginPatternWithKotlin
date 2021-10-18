package com.example.desginpatterkotlin.creational.factoryMethod

import java.lang.Exception


abstract class BoletoFM constructor(var valor: Float) {

    var juros: Float = 0.0f
    var desconto: Float = 0.0f
    var multa: Float = 0.0f

    fun calcularJuros(): Float {
        return valor * juros
    }

    fun calcularDesconto(): Float {
        return valor * desconto
    }

    fun calcularMulta(): Float {
        return valor * multa
    }

}

abstract  class BancoFM {
    fun gerarBoleto(vencimento: Int, valor: Float): BoletoFM {
        val boleto =criarBoleto(vencimento, valor)
        println("Boleto gerado com sucesso ${valor}")
        println("Valor do Juros; ${boleto.calcularJuros()}")
        println("Valor do desconto:  ${boleto.calcularDesconto()}")
        println("Valor da Multa ${boleto.calcularMulta()}")
        return boleto

    }
    abstract fun criarBoleto(vencimento: Int, valor: Float): BoletoFM
}

//************************
//BANCO CAIXA
class BancoBoleto10DiasCaixa(valor: Float) : BoletoFM(valor) {
    init {
        juros = 0.02f
        desconto = 0.1f
        multa = 0.05f
    }
}
class BancoBoleto20DiasCaixa(valor: Float) : BoletoFM(valor) {
    init {
        juros = 0.05f
        desconto = 0.05f
        multa = 0.1f
    }
}
class BancoBoleto30DiasCaixa(valor: Float) : BoletoFM(valor) {
    init {
        juros = 0.10f
        multa = 0.2f
    }
}
class BancoCaixa : BancoFM() {
    override fun criarBoleto(vencimento: Int, valor: Float): BoletoFM {
        when (vencimento) {
            10 -> return BancoBoleto10DiasCaixa(valor)
            20 -> return BancoBoleto20DiasCaixa(valor)
            30 -> return BancoBoleto30DiasCaixa(valor)
            else -> throw Exception("Vencimento Indisponivel")
        }

    }

}
//************************

//************************
//BANCO BRASIL
class BancoBoleto10DiasBrasil(valor: Float) : BoletoFM(valor) {
    init {
        juros = 0.03f
        desconto = 0.05f
        multa = 0.02f
    }
}
class BancoBoleto20DiasBrasil(valor: Float) : BoletoFM(valor) {
    init {
        juros = 0.05f
        desconto = 0.02f
        multa = 0.05f
    }
}
class BancoBoleto30DiasBrasil(valor: Float) : BoletoFM(valor) {
    init {
        juros = 0.10f
        multa = 0.15f
    }
}
class BancoBrasil : BancoFM() {
    override fun criarBoleto(vencimento: Int, valor: Float): BoletoFM {
                when (vencimento) {
                    10 -> return BancoBoleto10DiasBrasil(valor)
                    20 -> return BancoBoleto20DiasBrasil(valor)
                    30 -> return BancoBoleto30DiasBrasil(valor)
                    else -> throw Exception("Vencimento Indisponivel")
                }
    }
}
//************************



fun main() {
    println()
    println("BANCO CAIXA ")
    println()
    val banco = BancoCaixa()
    //10 dias
    banco.gerarBoleto(10, 100f)
    //20
    banco.gerarBoleto(20, 100f)
    //30
    banco.gerarBoleto(30, 100f)

    println()
    println("BANCO DO BRASIL ")
    println()

    val bb = BancoBrasil()
    //10 dias
    bb.gerarBoleto(10, 100f)
    //20
    bb.gerarBoleto(20, 100f)
    //30
    bb.gerarBoleto(30, 100f)
}