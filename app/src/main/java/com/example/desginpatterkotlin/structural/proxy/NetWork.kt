package com.example.desginpatterkotlin.structural.proxy

interface Batch {
    fun totalStudents(): Int
    fun registerStudent(name: String?)
}
class CourseBatch : Batch {
    private val listOfStudents: MutableList<String?>
    override fun totalStudents(): Int {
        return listOfStudents.size + 1
    }

    override fun registerStudent(name: String?) {
        listOfStudents.add(name)
        println(" Student name : $name")
    }

    init {
        listOfStudents = ArrayList()
    }
}

class ProxyBatch(private val batch: Batch) : Batch {
    override fun totalStudents(): Int {
        return batch.totalStudents()
    }

    override fun registerStudent(name: String?) {
        if (TOTAL_STUDENTS_ALLOWED_TO_BATCH >= totalStudents()) {
            batch.registerStudent(name)
        } else {
            println("Course batch size is : " + TOTAL_STUDENTS_ALLOWED_TO_BATCH)
            println("Batch is full so further students are not allowed ")
        }
    }

    companion object {
        private const val TOTAL_STUDENTS_ALLOWED_TO_BATCH = 10
    }
}

object TestProxyPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val courseBatch: Batch = CourseBatch()
        val proxyBatch: Batch = ProxyBatch(courseBatch)
        proxyBatch.registerStudent(" student 1")
        proxyBatch.registerStudent(" student 2")
        proxyBatch.registerStudent(" student 3")
        proxyBatch.registerStudent(" student 4")
        proxyBatch.registerStudent(" student 5")
        proxyBatch.registerStudent(" student 6")
        proxyBatch.registerStudent(" student 7")
        proxyBatch.registerStudent(" student 8")
        proxyBatch.registerStudent(" student 9")
        proxyBatch.registerStudent(" student 10")
        proxyBatch.registerStudent(" student 11")
        proxyBatch.registerStudent(" student 12")
        proxyBatch.registerStudent(" student 13")
    }
}