package com.example.desginpatterkotlin.creational.factoryMethod

import java.lang.Exception

class BoletoSimpleFactory {
    fun criarBoleto(vencimento: Int, valor: Float): BoletoFC {
        when (vencimento) {
            10 -> return BancoBoleto10DiasFC(valor)
            20 -> return BancoBoleto20DiasFC(valor)
            30 -> return BancoBoleto30DiasFC(valor)
            else -> throw Exception("Vencimento Indisponivel")
        }

    }
}

abstract class BoletoFC constructor(var valor: Float) {

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

class BancoBoleto10DiasFC(valor: Float) : BoletoFC(valor) {
    init {
        juros = 0.02f
        desconto = 0.1f
        multa = 0.05f
    }
}

class BancoBoleto20DiasFC(valor: Float) : BoletoFC(valor) {
    init {
        juros = 0.05f
        desconto = 0.05f
        multa = 0.1f
    }
}

class BancoBoleto30DiasFC(valor: Float) : BoletoFC(valor) {
    init {
        juros = 0.10f
        multa = 0.2f
    }
}

class BancoFC constructor(private val simpleFactory: BoletoSimpleFactory) {
    fun gerarBoleto(vencimento: Int, valor: Float): BoletoFC {
        val boleto = simpleFactory.criarBoleto(vencimento, valor)
        println("Boleto gerado com sucesso ${valor}")
        println("Valor do Juros; ${boleto.calcularJuros()}")
        println("Valor do desconto:  ${boleto.calcularDesconto()}")
        println("Valro da Multa ${boleto.calcularMulta()}")
        return boleto

    }
}

fun main() {

    val banco = BancoFC(BoletoSimpleFactory())
    //10 dias
    banco.gerarBoleto(10, 100f)
    //20
    banco.gerarBoleto(20, 100f)
    //30
    banco.gerarBoleto(30, 100f)
}