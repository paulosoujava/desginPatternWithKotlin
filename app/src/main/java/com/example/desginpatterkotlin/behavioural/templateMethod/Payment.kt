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
?? poss??vel encontrar caracter??sticas comuns nas classes acima:
??? Todas elas t??m o m??todo realizarCobranca() id??ntico.
??? Todas possuem um m??todo para c??lculo de taxas, mas s??o diferentes entre si.
??? Todas possuem um m??todo para c??lculo de desconto mas s??o diferentes entre si.
??? Todas possuem uma vari??vel de inst??ncia $valor.
??? Todas mant??m uma refer??ncia a um objeto Gateway.
 Vamos transformar o m??todo realizarCobranca() em
 nosso Template Method (m??todo de gabarito/modelo)
 e migrar tudo que ?? comum entre as classes para uma superclasse Pagamento.
 O m??todo calcularTaxa() ?? um hook (gancho). Trata-se de um m??todo que ?? implementado
 na classe abstrata mas recebe apenas uma implementa????o vazia, ou m??nima poss??vel como padr??o.
 */
abstract class PagamentoTM(var valor: Float, val gateway: Gateway){

       //Hook (gancho) - Implementa????o M??nima. Pode ser sobreescrito pelas subclasses.
    fun calcularTaxa() = 0F

    //Ser?? implementado pelas subclasses.
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