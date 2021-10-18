package com.example.desginpatterkotlin.creational.abstractFactory

interface Juros {
    fun getJuros(): Float
}

interface Desconto {
    fun getDesconto(): Float
}

interface Multa {
    fun getMulta(): Float
}

class CaixaJuros : Juros {
    override fun getJuros() = 0.02f
}

class BBJuros : Juros {
    override fun getJuros() = 0.03f
}

class CaixaDesconto : Desconto {
    override fun getDesconto() = 0.05f
}

class BBDesconto : Desconto {
    override fun getDesconto() = 0.04f
}

class CaixaMulta : Multa {
    override fun getMulta() = 0.05f //Retorna 5%.
}

class BBMulta : Multa {
    override fun getMulta() = 0.02f //Retorna 2%.
}

interface CalculosFactory {
    //Uma classe fábrica deve ter um método para criar um objeto correto de cada tipo.
    fun criarJuros(): Juros //Cria um objeto do tipo Juros.
    fun criarDesconto(): Desconto //Cria um objeto do tipo Desconto.
    fun criarMulta(): Multa //Cria um objeto do tipo Multa.
}

class CaixaCalculosFactory : CalculosFactory {
    override fun criarJuros(): Juros {
        return CaixaJuros() //Retorna um objeto do tipo Juros.
    }

    override fun criarDesconto(): Desconto {
        return CaixaDesconto() //Retorna um objeto do tipo Desconto.
    }

    override fun criarMulta(): Multa {
        return CaixaMulta() //Retorna um objeto do tipo Multa.
    }
}

class BBCalculosFactory : CalculosFactory {
    override fun criarJuros(): Juros {
        return BBJuros() //Retorna um objeto do tipo Juros.
    }

    override fun criarDesconto(): Desconto {
        return BBDesconto() //Retorna um objeto do tipo Desconto.
    }

    override fun criarMulta(): Multa {
        return BBMulta() //Retorna um objeto do tipo Multa.
    }
}

//Guarda localmente os objetos criados pela fábrica.
//A fábrica cria um objeto de Juros.
//A fábrica cria um objeto de Desconto.
//A fábrica cria um objeto de Multa.
//O boleto recebe em seu construtor o seu valor e a fábrica que deve utilizar
//para criar os cálculos.
class Boleto(v: Float, factory: CalculosFactory) {
    protected var valor: Float = v
    protected var juros: Juros //Guarda referência a um objeto do tipo Juros.
    protected var desconto: Desconto //Guarda referência a um objeto do tipo Juros.
    protected var multa: Multa //Guarda referência a um objeto do tipo Juros.

    init {
        juros = factory.criarJuros()
        desconto = factory.criarDesconto()
        multa = factory.criarMulta()
    }

    fun calcularJuros(): Float {
        //Retorna o valor do juros em reais, usa o método getJuros() do objeto do tipo Juros
        //criado pela fábrica.
        return valor * juros.getJuros();
    }

    fun calcularDesconto(): Float {
        //Retorna o valor do Desconto em reais, usa o método getDesconto() do objeto
        //do tipo Desconto criado pela fábrica.
        return valor * desconto.getDesconto()
    }

    fun calcularMulta(): Float {
        //Retorna o valor da Multa em reais, usa o método getMulta() do objeto do tipo Multa
        //criado pela fábrica.
        return valor * multa.getMulta()
    }
}

class Banco {
    //O Banco recebe no método gerarBoleto() o valor do boleto a ser criado
    //e a fábrica que deve utilizar para criar os cálculos.
    fun gerarBoleto(valor: Float, factory: CalculosFactory): Boleto {
        //Passa o valor e a fábrica para o boleto.
        //Boleto utiliza a fábrica recebida para o Juros, Desconto e Multa.
        val boleto = Boleto(valor, factory)
        //Apenas imprime os resultados dos cálculos do boleto que foi criado.
        println("Boleto gerado com sucesso no valor de R$  $valor ")
        println("Valor Juros: R$' . ${boleto.calcularJuros()} ")
        println("Valor Desconto: R$' . ${boleto.calcularDesconto()} ")
        println("Valor Multa: R$' . ${boleto.calcularMulta()} ")
        println("---------------")
        return boleto;
    }
}

fun main() {
    //Criação de um objeto Banco.
    val banco =  Banco()
//Criação de uma fábrica calculos para boletos da Caixa.
    val factoryCaixa =  CaixaCalculosFactory()
//Criação de uma fábrica calculos para boletos do Banco do Brasil.
    val factoryBancoBrasil =  BBCalculosFactory()
//Criação de um boleto da Caixa.
    println( "### Boleto da Caixa ###")
    banco.gerarBoleto( 100f, factoryCaixa)
//Criação de um boleto do Banco do Brasil.
    println( "### Boleto do Banco do Brasil ###")
    banco.gerarBoleto(100f, factoryBancoBrasil)
}