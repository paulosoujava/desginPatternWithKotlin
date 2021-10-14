package com.example.desginpatterkotlin.behavioural.state

import java.lang.Exception

class Pedido(private var estadoAtual: Int = AGUARDANDO_PAGAMENTO) {
    companion object {
        const val AGUARDANDO_PAGAMENTO = 1
        const val PAGO = 2
        const val CANCELADO = 3
        const val ENVIADO = 4
    }

    fun sucessoAoPaga() {
        if (estadoAtual == AGUARDANDO_PAGAMENTO) {
            estadoAtual = PAGO
        } else {
            println("Pedido sem status")
        }
    }

    fun cancelarPedido() {
        if (estadoAtual == AGUARDANDO_PAGAMENTO || estadoAtual == PAGO) {
            estadoAtual = CANCELADO
        } else {
            println("Pedido não pode ser cancelado")
        }
    }

    fun despacharPedido() {
        if (estadoAtual == PAGO) {
            estadoAtual = ENVIADO
        } else {
            println("Pedido não pode ser enviado")
        }
    }
}

fun main() {
    val pedido = Pedido()
    pedido.sucessoAoPaga()
    //*pedido.cancelarPedido()
    pedido.despacharPedido()


    //with state
    val pedidoState = PedidoState()
    pedidoState.realizarPagamento()
    pedidoState.despacharPedido()
}

//with state
interface State {
    fun sucessoAoPager()
    fun despacharPedido()
    fun cancelarPedido()
}

class PedidoState() {



    var aguardando: State = AguardandoPagamentoState(this)
    var pago: State = PagoState(this)
    var cancelado: State = CanceladoState(this)
    var enviado: State = EnviadoState(this)

    private  var estadoAtual = this.aguardando

    fun realizarPagamento() {
        estadoAtual.sucessoAoPager()
    }

    fun cancelarPedido() {
        estadoAtual.cancelarPedido()
    }

    fun despacharPedido() {
        estadoAtual.despacharPedido()
    }

    fun setEstadoAtual(state: State) {
        this.estadoAtual = state
    }
}

class AguardandoPagamentoState(private val pedido: PedidoState) : State {
    override fun sucessoAoPager() {
        pedido.setEstadoAtual(pedido.pago)
    }

    override fun despacharPedido() {
        throw  Exception("operacao nao suportada")
    }

    override fun cancelarPedido() {
        pedido.setEstadoAtual(pedido.cancelado)
    }

}

class PagoState(private val pedido: PedidoState) : State {
    override fun sucessoAoPager() {
        throw  Exception("operacao nao suportada")
    }

    override fun despacharPedido() {
        pedido.setEstadoAtual(pedido.enviado)
    }

    override fun cancelarPedido() {
        pedido.setEstadoAtual(pedido.cancelado)
    }

}

class CanceladoState(private val pedido: PedidoState) : State {
    override fun sucessoAoPager() {
        throw  Exception("operacao nao suportada")
    }

    override fun despacharPedido() {
        throw  Exception("operacao nao suportada")
    }

    override fun cancelarPedido() {
        throw  Exception("operacao nao suportada")
    }

}

class EnviadoState(private val pedido: PedidoState) : State {
    override fun sucessoAoPager() {
        throw  Exception("operacao nao suportada")
    }

    override fun despacharPedido() {
        throw  Exception("operacao nao suportada")
    }

    override fun cancelarPedido() {
        throw  Exception("operacao nao suportada")
    }

}