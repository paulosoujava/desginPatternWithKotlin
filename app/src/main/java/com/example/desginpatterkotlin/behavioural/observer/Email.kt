package com.example.desginpatterkotlin.behavioural.observer

interface MyObserver {
    fun update(mensagem: String)
    fun getNome(): String
    fun getEmail(): String

}

interface Subject {
    fun registerObserver(observer: MyObserver)
    fun removeObserver(observer: MyObserver)
    fun notifyObservers()
}

class Email {
    companion object {
        fun enviar(observer: MyObserver, mensagem: String) {
            println("Email enviado para ${observer.getNome()} ${observer.getEmail()}")
            println("Mensagem:  ${mensagem}")
        }
    }
}

class Cliente(private var nome: String, private var email: String, val subject: Subject) :
    MyObserver {

    init {
        subject.registerObserver(this)
    }

    override fun update(mensagem: String) {
        Email.enviar(this, mensagem)
    }

    override fun getEmail() = email
    override fun getNome() = nome
}

class Parceiro(private var nome: String, private var email: String, val subject: Subject) :
    MyObserver {
    init {
        subject.registerObserver(this)
    }

    override fun update(mensagem: String) {
        Email.enviar(this, mensagem)
    }

    override fun getEmail() = email
    override fun getNome() = nome
}

class Fornecedor(private var nome: String, private var email: String, val subject: Subject) :
    MyObserver {
    init {
        subject.registerObserver(this)
    }

    override fun update(mensagem: String) {
        Email.enviar(this, mensagem)
    }

    override fun getEmail() = email
    override fun getNome() = nome
}

class NewsLatter : Subject {
    val observers = mutableListOf<MyObserver>()
    val mensagens = mutableListOf<String>()

    /*Adiciona um objeto a lista de observers a serem notificados
    quando uma nova mensagem for inserida na newsletter. */

    override fun registerObserver(observer: MyObserver) {
        observers.add(observer)
    }
/*Remove um objeto a lista de observers a serem notificados
      quando uma nova mensagem for inserida na newsletter. */

    override fun removeObserver(observer: MyObserver) {
        observers.remove(observer)
    }

    //Notifica todos os observer sobre a nova mensagem da newsletter.
    override fun notifyObservers() {
        observers.forEach {
            it.update(mensagens.last())
        }
    }

    /*Adiciona uma nova mensagem a newsletter e solicita que todos
         os observers sejam notificados */
    fun addMensagem(mensagem: String) {
        mensagens.add(mensagem)
        this.notifyObservers()
    }
}

fun main() {
    val newsLatter = NewsLatter()

    val paulo = Cliente("Paulo" , "paulosoujava@gmail.com", newsLatter)
    val malu = Parceiro("Malu" , "malu@gmail.com", newsLatter)
    val bruna = Fornecedor("Bruna" , "bruna@gmail.com", newsLatter)
    newsLatter.addMensagem("Esta é a primeira mensagem para todos")
    println()
    newsLatter.removeObserver(paulo)
    newsLatter.addMensagem("Esta é a segunda mensagem menos para paulo")
    println()
    newsLatter.removeObserver(malu)
    newsLatter.addMensagem("Esta é a terceira mensagem menos para paulo e malu")
    println()
    newsLatter.removeObserver(bruna)
    newsLatter.registerObserver(malu)
    newsLatter.registerObserver(paulo)
    newsLatter.addMensagem("Esta é a terceira mensagem para malu e paulo")
}