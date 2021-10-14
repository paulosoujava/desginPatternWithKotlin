package com.example.desginpatterkotlin.behavioural.strategy

abstract class Pedido(var valor: Float) {
    abstract fun calculaFreteComum(): Double
    abstract fun calculaFreteExpresso(): Double
}

class PedidoEletronico(valor: Float) : Pedido(valor) {
    val name = "ELETRONICOS"
    override fun calculaFreteComum(): Double {
        return valor * 0.05; // valor do comum  5% pedidos eletronicos
    }

    override fun calculaFreteExpresso(): Double {
        return valor * 0.2; // valor do comum  20% pedidos eletronicos
    }
}

class PedidoMoveis(valor: Float) : Pedido(valor) {
    val name = "MOVEIS"
    override fun calculaFreteComum(): Double {
        return valor * 0.02; // valor do comum  2% pedidos moveis
    }

    override fun calculaFreteExpresso(): Double {
        return valor * 0.1; // valor do comum  10% pedidos moveis
    }
}

//VAMOS APLICAR O PADRÃO STRATEGY NO EXEMPLO ACIMA
// o erro acima? imagene que vc tenha 500 subclasses e tivesse que mudar o:
// o valor dos fretes  calculaFreteExpresso calculaFreteComum
//O padrão Strategy encapsula algoritmos que representam um comportamento similar
interface Frete {
    fun calcula(valor: Float): Double
}

class FreteComun : Frete {
    override fun calcula(valor: Float) = valor * 0.05
}

class FreteExpresso : Frete {
    override fun calcula(valor: Float) = valor * 0.1
}

abstract class PedidoStrategy(var valor: Float, val frete: Frete) {
    fun calculaFrete(): Double {
        return frete.calcula(valor)
    }
}

//now remove calculet from moveis and eletronicos
class PedidoEletronicoStrategy(valor: Float, frete: Frete) : PedidoStrategy(valor, frete) {
    val name = "ELETRONICOS"
}

class PedidoMoveisStrategy(valor: Float, frete: Frete) : PedidoStrategy(valor, frete) {
    val name = "MOVEIS"
}


fun main() {
    println("NO STRATEGY")
    val pedido = PedidoEletronico(100F)
    println(pedido.name)
    println("Frete comum ${pedido.calculaFreteComum()}")
    println("Frete expresso ${pedido.calculaFreteExpresso()}")

    val movel = PedidoMoveis(100F)
    println(movel.name)
    println("Frete comum ${movel.calculaFreteComum()}")
    println("Frete expresso ${movel.calculaFreteExpresso()}")
    println()
    println("WITH STRATEGY")
    val freteComun = FreteComun()
    val freteExpresso = FreteExpresso()
    val pedidoEletronicoFC = PedidoEletronicoStrategy(50F, freteComun)
    val pedidoEletronicoFE = PedidoEletronicoStrategy(50F, freteExpresso)
    println("Pedido ${pedidoEletronicoFC.name} ${pedidoEletronicoFC.calculaFrete()}")
    println("Pedido ${pedidoEletronicoFE.name} ${pedidoEletronicoFE.calculaFrete()}")
    val pedidoMoveisFC = PedidoMoveisStrategy(50F, freteComun)
    val pedidoMoveisFE = PedidoMoveisStrategy(50F, freteExpresso)
    println("Pedido ${pedidoMoveisFC.name} ${pedidoMoveisFC.calculaFrete()}")
    println("Pedido ${pedidoMoveisFE.name} ${pedidoMoveisFE.calculaFrete()}")

}


/*
1. Programe para abstrações: a classe Pedido não depende diretamente
   de nenhum calculador de frete concreto e sim da interface Frete.
2. Open-closed principle: o Pedido não terá nenhum impacto um novo
   tipo de frete seja aceito pelo e-commerce. Bastaria criar uma nova
   classe de frete que implemente a interface Frete.
3. De prioridade a composição em relação à herança: ao invés de herdar
   os cálculos de frete os pedidos obtém a capacidade de calcular os fretes
   ao serem compostos pelo objeto do tipo Frete que lhe convém.
 */