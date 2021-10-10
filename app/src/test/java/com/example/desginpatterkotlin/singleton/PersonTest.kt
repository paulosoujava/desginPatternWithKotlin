package com.example.desginpatterkotlin.singleton

import org.junit.Assert.*
import org.junit.Test

class PersonTest{

    @Test
    fun `check if not a singleton`(){
        val paulo = Person("PAULO")
        val jose = Person("JOSE")
        assertNotEquals(paulo,jose)

        val paulo2 = PersonUnique
        paulo2.name = "PAULO"
        val jose2 = PersonUnique
        jose2.name = "JOSE"

        assertEquals(paulo2, jose2)
    }
    @Test
    fun `check if  a singleton`(){
        val paulo = PersonUnique
        paulo.name = "PAULO"
        val jose = PersonUnique
        jose.name = "JOSE"

        assertEquals(paulo, jose)
        assertEquals(paulo.name, jose.name)
    }
}