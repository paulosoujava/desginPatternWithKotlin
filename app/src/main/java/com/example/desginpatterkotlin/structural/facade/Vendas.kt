package com.example.desginpatterkotlin.structural.facade

class Pedido(var consumidor: Consumidor){

    var valor: Float= 0F
    var produtos: MutableList<Produto> = arrayListOf()

    fun total() =  produtos.map {  it.valor}.sum()
    fun addProduto(produto: Produto){
        produtos.add(produto)
        valor = total()
    }

}

class VendaFacade(consumidor: Consumidor) {
    private val pedido: Pedido = Pedido(consumidor)
    private val emailPedido: EmailPedido = EmailPedido(pedido)
    private var pagamento: Pagamento? = null

    fun addProduto(produto: Produto){
        pedido.addProduto(produto)
    }

    fun pedidoCredito(){
        pagamento = PagamentoCredito(pedido)
        email(pagamento as PagamentoCredito)
    }
    fun pedidoBoleto(){
        pagamento = PagamentoBoleto(pedido)
        email(pagamento as PagamentoBoleto)
    }
    fun email(pagamento: Pagamento){
        if(pagamento.realizaPagamento()){
            emailPedido.enviarEmail("Pagamento realizado com sucesso via ${pagamento.type}")
        }else{
            emailPedido.enviarEmail("Falha ao enviar pagamento via  ${pagamento.type}")
        }
    }
}


abstract class Pagamento(val pedido:Pedido){
    abstract var type: String
    abstract fun realizaPagamento():Boolean
}

class PagamentoCredito constructor( pedido: Pedido) : Pagamento(pedido) {
    override var type = "CREDITO"
    override fun realizaPagamento()= pedido.valor > 0
}
class PagamentoBoleto constructor( pedido: Pedido) : Pagamento(pedido) {
    override var type = "BOLETO"
    override fun realizaPagamento()= pedido.valor > 0
}

class EmailPedido constructor(val pedido: Pedido){
    fun enviarEmail(mensagem:String){
        println("Email enviado para ${pedido.consumidor.nome} :: $mensagem :: ${pedido.total()}" )
    }
}

class Consumidor constructor( var  nome:String,  var cpf: String, var email:String)
class Produto constructor( var nome:String,  var descricao:String,  var valor:Float)


fun main() {
    val consumidor = Consumidor("Paulo", "123321213231", "paulosoujava@gmail.com")
    val p1 = Produto("mac air", "computador", 9.500F)
    val p2 = Produto("teclado", "acessorios", 1500F)
    val p3 = Produto("mouse", "acessorios", 500F)
    //**********************************************************************
    // SEM FACADE
    //**********************************************************************
    println("SEM FACADE ")
    val pedido = Pedido(consumidor)
    pedido.addProduto(p1)
    pedido.addProduto(p2)
    pedido.addProduto(p3)

    val pagamento = PagamentoBoleto(pedido) // 1
    val email = EmailPedido(pedido) // 2

    if(pagamento.realizaPagamento()){
        email.enviarEmail("Pagamento realizado com sucesso via boleto")
    }else{
        email.enviarEmail("Falha ao enviar pagamento via boleto")
    }
    println()
    //**********************************************************************
    //COM FACADE
    //**********************************************************************
    println("COM FACADE ")
    val vendaFacade = VendaFacade(consumidor)
    vendaFacade.addProduto(p1)
    vendaFacade.addProduto(p2)
    vendaFacade.addProduto(p3)
    vendaFacade.pedidoBoleto()
    vendaFacade.pedidoCredito()
}