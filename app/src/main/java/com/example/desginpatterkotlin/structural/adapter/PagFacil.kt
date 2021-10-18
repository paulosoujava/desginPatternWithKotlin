package com.example.desginpatterkotlin.structural.adapter

import com.example.desginpatterkotlin.behavioural.state.PagoState

interface Gateway {
    fun setValor(valor: Float)
    fun setParcelas(parcelas: Int)
    fun setNumeroCartao(numeroCartao: String)
    fun setCVV(cvv: String)
    fun validarCartao(): Boolean
    fun realizarPagamento(): Boolean

}

class Cobranca constructor(private var gateway: Gateway) {

    //Todos os métodos abaixo delegam responsabilidades para os métodos de $gateway.
    @JvmName("setGateway1")
    fun setGateway(gateway: Gateway) {
        this.gateway = gateway
    }

    fun setValor(valor: Float) {
        this.gateway.setValor(valor)
    }

    fun setParcelas(parcelas: Int) {
        this.gateway.setParcelas(parcelas)
    }

    fun setNumeroCartao(numeroCartao: String) {
        this.gateway.setNumeroCartao(numeroCartao)
    }

    fun setCVV(cvv: String) {
        this.gateway.setCVV(cvv)
    }

    fun realizarPagamento(): Boolean {
        if (this.gateway.validarCartao()) {
            return this.gateway.realizarPagamento()
        }
        return false
    }

}

open class PagFacil {
    fun setValor(valor: Float) {}
    fun setParcelas(parcelas: Int) {}
    fun setNumeroCartao(numeroCartao: String) {}
    fun setCVV(cvv: String) {}
    fun validarCartao(): Boolean {
        return true
    }

    fun realizarPagamento(): Boolean {
        return true
    }
}

class PagFacilAdapter : PagFacil(), Gateway {

}

open class TopPagamentos {
    fun setValor(valor: Float) {}
    fun setQuantidadeParcelas(parcelas: Int) {}
    fun setNumeroCartao(numeroCartao: String, cvv: String) {}
    fun realizarPagamento(): Boolean {
        return true
    }
}

class TopPagamentosAdapter constructor(val topPagamentos: TopPagamentos) : Gateway {
     var numCartao: String = ""
     var cvv: String = ""


    override fun setValor(valor: Float) {
        this.topPagamentos.setValor(valor)
    }

    override fun setParcelas(parcelas: Int) {
        this.topPagamentos.setQuantidadeParcelas(parcelas)
    }

    override fun setNumeroCartao(numeroCartao: String) {

        //TopPagamento não possui o método setNumeroCartao();
        //Então vamos guardar o número do cartão em uma variável local.

        //TopPagamento não possui o método setNumeroCartao();
        //Então vamos guardar o número do cartão em uma variável local.
        this.numCartao = numCartao
        //Sabemos que o número do cartão já foi definido.
        //Se o CVV também já foi definido. Chama o método setCartao().
        //Sabemos que o número do cartão já foi definido.
        //Se o CVV também já foi definido. Chama o método setCartao().
        if (this.cvv != null) {
            this.topPagamentos.setNumeroCartao(this.numCartao, this.cvv)
        }
    }

    override fun setCVV(cvv: String) {
        //TopPagamento não possui o método setCVV();
        //Então vamos guardar o CVV em uma variável local.
        this.cvv = cvv
        //Sabemos que o CVV já foi definido.
        //Se o número do cartão também já foi definido. Chama o método setCartao().
        if (this.numCartao != null) {
            this.topPagamentos.setNumeroCartao(this.numCartao, this.cvv)
        }
    }

    override fun validarCartao(): Boolean {
        return true
    }

    override fun realizarPagamento(): Boolean {
        return this.topPagamentos.realizarPagamento()
    }
}

fun main() {
    //==== Classes dos fornecedores ====
//E uma instância da classe de TopPagamentos.
    val topPagamentos = TopPagamentos()
//==== Adapters ====
//Criação do adaptador de PagFacil.
    val pagFacilAdapter = PagFacilAdapter()
//Criação do adaptador de TopPagamentos.
    val topPagamentosAdapter = TopPagamentosAdapter(topPagamentos)
//==== Cobranca ====
    println("Cobrança utilizando PagFacil como Gateway ")
//Criação de uma Cobrança utilidando a classe PagFacil.
//Repare que o adaptador de $pagFacil é passado para o construtor.
    val cobranca = Cobranca(pagFacilAdapter);
    cobranca.setValor(100F);
    cobranca.setParcelas(3);
    cobranca.setNumeroCartao(1234123412341234.toString());
    cobranca.setCVV(123.toString());
    if (cobranca.realizarPagamento()) {
        println(" Pagamento Realizado com sucesso ")
    } else {
        println(" O pagamento falhou")
    }
//Cobrança utilidando a classe PagFacil.
    println("Cobrança utilizando TopPagamentos como Gateway")
//Troca do Gateway de Cobrança para TopPagamentos
    cobranca.setGateway(topPagamentosAdapter);
    cobranca.setValor(100F);
    cobranca.setParcelas(3);
    cobranca.setNumeroCartao(1234123412341234.toString());
    cobranca.setCVV(123.toString());
    if (cobranca.realizarPagamento()) {
        println("Pagamento Realizado com sucesso")
    } else {
        println("O pagamento falhou")
    }
}