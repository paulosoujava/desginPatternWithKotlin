package com.example.desginpatterkotlin.structural.adapter

interface CurrencyConverter{
    fun convertCurrency(countryName: String)
}

class CurrencyToDollarConverter: CurrencyConverter{
     override fun convertCurrency(countryName: String) {
         println("Dollar Currency")
     }
 }

class CurrencyToEuroConverter{
    fun convertCurrency(isoCode: String){
        println("Euro currency")
    }
}

class CurrencyConverterAdapter(private val currencyToEuroConverter: CurrencyToEuroConverter): CurrencyConverter{
    override fun convertCurrency(countryName: String) {
        val currencyIsoCode = getCurrencyIsoCode()
        currencyToEuroConverter.convertCurrency(currencyIsoCode)
    }
    private fun getCurrencyIsoCode(): String = "This is ISO code"
}
fun main() {
    val currencyToDollarConverter = CurrencyToDollarConverter()
    currencyToDollarConverter.convertCurrency("Country name")

    val currencyToEuroConverter = CurrencyToEuroConverter()
    val currencyConverterAdapter = CurrencyConverterAdapter(currencyToEuroConverter)
    currencyConverterAdapter.convertCurrency("Country name")
}