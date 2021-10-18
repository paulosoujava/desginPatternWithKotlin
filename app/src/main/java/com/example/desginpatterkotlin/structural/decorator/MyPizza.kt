package com.example.desginpatterkotlin.structural.decorator

abstract class MyPizza(
    var comBordaRequeijao: Boolean = false,
    var comMassaIntegral: Boolean = false
) {

    abstract var preco: Float
    abstract var descricao: String

    fun adicionaBordaRequeijao() {
        comBordaRequeijao = true
    }

    fun adicionaMassaIntegral() {
        comMassaIntegral = true
    }

}

class PizzaFrango(
    override var preco: Float = 19F,
    override var descricao: String = "Deliciosa pizza de frango"
) : MyPizza()

class PizzaCalabreza(
    override var preco: Float = 25F,
    override var descricao: String = "Deliciosa pizza de PizzaCalabresa"
) : MyPizza()

class PizzaQueijo(
    override var preco: Float = 22F,
    override var descricao: String = "Deliciosa pizza de queijo"
) : MyPizza()

//O decorator precisa manter uma referência ao objeto decorado.
abstract class AcrescimoDecorator constructor(var myPizza: MyPizza) : MyPizza() {
    //Vamos forçar que cada decorador implemente sua própria descrição.
    //Para concatenar a  descrição do acréscimo a descrição da pizza.
    //Vamos forçar que cada decorador implemente seu preço.
    //Para somar o preço do acréscimo ao preço da pizza.
}

class BordaRequeijao(myPizza: MyPizza) : AcrescimoDecorator(myPizza) {
    override var preco: Float =  myPizza.preco + 8.50F
    override var descricao =  myPizza.descricao + "+ Borda recheada de requeijão"
}
class MassaIntegral(myPizza: MyPizza) : AcrescimoDecorator(myPizza) {
    override var preco: Float =  myPizza.preco + 5F
    override var descricao =  myPizza.descricao + "+ Massa integral"
}

fun main() {
    val pizzaQueijo = PizzaQueijo()
    println("DESC ${pizzaQueijo.descricao}")
    println("VALOR ${pizzaQueijo.preco}")
    //Um decorator é criado e passa a englobar a pizza
    val pizzaBordaQueijo = BordaRequeijao(pizzaQueijo)
    println("DESC ${pizzaBordaQueijo.descricao}")
    println("VALOR ${pizzaBordaQueijo.preco}")
    //Um decorator é criado e passa a englobar a pizza
    val pizzaQueijoBordaMassaIntegral = MassaIntegral(pizzaQueijo)
    println("DESC ${pizzaQueijoBordaMassaIntegral.descricao}")
    println("VALOR ${pizzaQueijoBordaMassaIntegral.preco}")
}