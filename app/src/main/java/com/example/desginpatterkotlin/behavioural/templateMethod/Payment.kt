package com.example.desginpatterkotlin.behavioural.templateMethod

import kotlin.random.Random

class Gateway {
    fun cobrar( valor: Float): Boolean{
        val list = listOf(true, false)
        return list.get(Random.nextInt(0,1))
    }
}

class PagamentosCredito(private var valor: Float, val gateway: Gateway){
    fun calcularTaxa():Float{
        return (valor * 0.05).toFloat()
    }
    fun calcularDesconto():Float{
        if(valor > 300){
            return (valor * 0.02).toFloat()
        }
        return 0f
    }
    fun relizaCobranca(): Boolean{
        var valorFinal = valor + calcularTaxa() - calcularDesconto()
        return gateway.cobrar(valorFinal)
    }
}

class PagamentosDebito(private var valor: Float, val gateway: Gateway){
    fun calcularTaxa() = 4

    fun calcularDesconto():Float{
            return (valor * 0.05).toFloat()

    }
    fun relizaCobranca(): Boolean{
        var valorFinal = valor + calcularTaxa() - calcularDesconto()
        return gateway.cobrar(valorFinal)
    }
}

class PagamentosDinheiro(private var valor: Float, val gateway: Gateway){
    fun calcularTaxa() = 0

    fun calcularDesconto():Float{
        return (valor * 0.01).toFloat()

    }
    fun relizaCobranca(): Boolean{
        var valorFinal = valor + calcularTaxa() - calcularDesconto()
        return gateway.cobrar(valorFinal)
    }
}

fun main() {
    val valor = 100f
    val gateway = Gateway()

    println("CREDITO")
    println(PagamentosCredito(valor,gateway).relizaCobranca())

    println("DEBITO")
    println(PagamentosDebito(valor,gateway).relizaCobranca())

    println("DINHEIRO")
    println(PagamentosCredito(valor,gateway).relizaCobranca())

    println("TEMPLATE METHOD")
    val valorTm = 100f
    val gatewayTm = Gateway()

    println("CREDITO TM")
    println(PagamentosCreditoTM(valorTm, gatewayTm).relizaCobranca())

    println("DEBITO TM")
    println(PagamentosDebitoTM(valorTm, gatewayTm).relizaCobranca())

    println("DINHEIRO TM")
    println(PagamentosCreditoTM(valorTm, gatewayTm).relizaCobranca())

}
/*
É possível encontrar características comuns nas classes acima:
● Todas elas têm o método realizarCobranca() idêntico.
● Todas possuem um método para cálculo de taxas, mas são diferentes entre si.
● Todas possuem um método para cálculo de desconto mas são diferentes entre si.
● Todas possuem uma variável de instância $valor.
● Todas mantêm uma referência a um objeto Gateway.
 Vamos transformar o método realizarCobranca() em
 nosso Template Method (método de gabarito/modelo)
 e migrar tudo que é comum entre as classes para uma superclasse Pagamento.
 O método calcularTaxa() é um hook (gancho). Trata-se de um método que é implementado
 na classe abstrata mas recebe apenas uma implementação vazia, ou mínima possível como padrão.
 */
abstract class PagamentoTM(var valor: Float, val gateway: Gateway){

       //Hook (gancho) - Implementação Mínima. Pode ser sobreescrito pelas subclasses.
    fun calcularTaxa() = 0F

    //Será implementado pelas subclasses.
    abstract fun calcularDesconto():Float

    fun relizaCobranca(): Boolean{
        var valorFinal = valor + calcularTaxa() - calcularDesconto()
        return gateway.cobrar(valorFinal)
    }
}
class PagamentosCreditoTM(valor: Float, gateway: Gateway): PagamentoTM(valor, gateway){
    override fun calcularDesconto(): Float {
        if(valor > 300){
            return (valor * 0.02).toFloat()
        }
        return 0f
    }
     fun calculaTaxa(): Float{
         return (valor * 0.05).toFloat()
    }
}
class PagamentosDebitoTM(valor: Float, gateway: Gateway): PagamentoTM(valor, gateway){
    override fun calcularDesconto(): Float {
        return (valor * 0.05).toFloat()

    }
    fun calculaTaxa(): Float{
        return 4F
    }
}
class PagamentosDinheiroTM(valor: Float, gateway: Gateway): PagamentoTM(valor, gateway){
    override fun calcularDesconto(): Float {
        return (valor * 0.1).toFloat()

    }
}