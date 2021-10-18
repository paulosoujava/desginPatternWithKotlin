package com.example.desginpatterkotlin.creational.builder

class Component private constructor(builder: Builder) {
    var param1: String? = null
    var param2: Int? = null
    var param3: Boolean? = null

    class Builder {
        private var param1: String? = null
        private var param2: Int? = null
        private var param3: Boolean? = null

        fun setParam1(param1: String) = apply { this.param1 = param1 }
        fun setParam2(param2: Int) = apply { this.param2 = param2 }
        fun setParam3(param3: Boolean) = apply { this.param3 = param3 }
        fun build() = Component(this)

        fun getParam1() = param1
        fun getParam2() = param2
        fun getParam3() = param3
    }

    init {
        param1 = builder.getParam1()
        param2 = builder.getParam2()
        param3 = builder.getParam3()
    }
}
//other ex:

data class Mails(// Stays the same
    val to: String,
    var title: String = "",
    val message: String = "",
    var cc: List<String> = listOf(),
    val bcc: List<String> = listOf(),
    val attachments: List<java.io.File> = listOf()
)

class MailBuilder(val to: String) {
    private var mail: Mails = Mails(to)
    fun title(title: String): MailBuilder {
        mail.title = title
        return this
    }
    fun cc(cc: List<String>): MailBuilder {
        mail.cc = cc
        return this
    }
    // Repeated for other properties
    fun build(): Mails {
        return mail
    }
}

fun main() {

    val email = MailBuilder("hello@hello.com")
        .title("What's up?")
        .build()    // Message)

    MailBuilder("hello@hello.com")
        .title("What's up?")
        .cc(listOf()) // cc
        .build()    // Message)

}