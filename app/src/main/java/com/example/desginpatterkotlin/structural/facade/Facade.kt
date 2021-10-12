package com.example.desginpatterkotlin.structural.facade

enum class Language {
    English,
    Italian,
    French;
}

interface Translator {
    fun translate(text: String, textLanguage: Language)
}

class ItalianTranslator : Translator {
    override fun translate(text: String, textLanguage: Language) {
        println("Translate ($text) from ${textLanguage.name} to Italian")
    }
}

class FrenchTranslator : Translator {
    override fun translate(text: String, textLanguage: Language) {
        println("Translate ($text) from ${textLanguage.name} to French")
    }
}

class EnglishTranslator : Translator {
    override fun translate(text: String, textLanguage: Language) {
        println("Translate ($text) from ${textLanguage.name} to English")
    }
}

class TranslationManager {
    private val italianTranslator = ItalianTranslator()
    private val frenchTranslator = FrenchTranslator()
    private val englishTranslator = EnglishTranslator()

    fun translate(text: String, translateFrom: Language, translateTo: Language) {
        when (translateTo) {
            Language.Italian -> italianTranslator.translate(text, translateFrom)
            Language.French -> frenchTranslator.translate(text, translateFrom)
            Language.English -> englishTranslator.translate(text, translateFrom)
        }
    }
}

fun main() {
    val translationManager = TranslationManager()
    translationManager.translate("Some text", Language.English, Language.Italian)
    translationManager.translate("Some text", Language.English, Language.French)
}