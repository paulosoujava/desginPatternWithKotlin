package com.example.desginpatterkotlin.creational.factoryMethod

import java.lang.Exception

abstract class Boleto constructor(var valor: Float){

    var juros: Float = 0.0f
    var desconto: Float = 0.0f
    var multa:Float = 0.0f

    fun calcularJuros():Float{
        return valor * juros
    }
    fun calcularDesconto():Float{
        return valor * desconto
    }
    fun calcularMulta():Float{
        return valor * multa
    }

}

class BancoBoleto10Dias(valor: Float) : Boleto(valor) {
    init {
        juros = 0.02f
        desconto = 0.1f
        multa = 0.05f
    }
}
class BancoBoleto20Dias(valor: Float) : Boleto(valor) {
    init {
        juros = 0.05f
        desconto = 0.05f
        multa = 0.1f
    }
}
class BancoBoleto30Dias(valor: Float) : Boleto(valor) {
    init {
        juros = 0.10f
        multa = 0.2f
    }
}
class Banco {
    fun gerarBoleto(vencimento: Int, valor: Float) : Boleto{
        var boleto: Boleto
            when(vencimento){
            10 ->  boleto = BancoBoleto10Dias(valor)
            20 ->  boleto = BancoBoleto20Dias(valor)
            30 ->  boleto = BancoBoleto30Dias(valor)
            else -> throw Exception("Vencimento Indisponivel")
        }

        println("Boleto gerado com sucesso ${valor}")
        println("Valor do Juros; ${boleto.calcularJuros()}")
        println("Valor do desconto:  ${boleto.calcularDesconto()}")
        println("Valro da Multa ${boleto.calcularMulta()}")

        return boleto
    }
}

fun main() {
    val banco = Banco()
    //10 dias
    banco.gerarBoleto(10, 100f)
    //20
    banco.gerarBoleto(20, 100f)
    //30
    banco.gerarBoleto(30, 100f)
}