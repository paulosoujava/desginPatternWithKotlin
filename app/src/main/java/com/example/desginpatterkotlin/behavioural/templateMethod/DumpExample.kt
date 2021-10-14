package com.example.desginpatterkotlin.behavioural.templateMethod

abstract class Abstract {
    abstract fun abstractMethod(): String;
    open fun hookMethod() = "OMG I am a hook!";
    fun templateMethod() {
        print(abstractMethod());
        print(hookMethod());
    }
}

class Concrete : Abstract() {
    override fun abstractMethod() = "This is a boring example...";
}

class ConcreteOverridingHook : Abstract() {
    override fun abstractMethod() = "So, so boring...";
     override fun hookMethod() = "I'm an overriden hook method!";
}

fun main() {
    var con1 = Concrete();
    var con2 = ConcreteOverridingHook();
    con1.templateMethod();
    con2.templateMethod();

    /*
      This is a boring example...
      OMG I am a hook!
      So, so boring...
      I'm an overriden hook method!
    */
}