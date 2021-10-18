package com.example.desginpatterkotlin.structural.adapter


//3rd party funcionality
data class DisplayDataType(val index: Float, val data: String)

class DataDisplay{
    fun displayData(data:DisplayDataType){
        println("Data is displayed: ${data.index} - ${data.data}")
    }
}


//-------------
//our code
data class DatabaseData(val position: Int, val amount: Int)

class DatabaseDataGenerator{
    fun generateData(): List<DatabaseData>{
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(2,2))
        list.add(DatabaseData(3,3))
        list.add(DatabaseData(4,4))
        list.add(DatabaseData(5,5))
        list.add(DatabaseData(6,6))
        return list
    }
}
interface DatabaseDataConverter{
    fun convertData(data: List<DatabaseData>):List<DisplayDataType>
}

class DataDisplayAdapter(private val display: DataDisplay): DatabaseDataConverter {
    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
        val returnList: ArrayList<DisplayDataType> = arrayListOf()
        for(datum: DatabaseData in data){
            val ddt = DisplayDataType(datum.position.toFloat(), datum.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }
}

fun main() {
    val generator = DatabaseDataGenerator()
    val generatorData = generator.generateData()
    println("WITHOUT ADAPTER")
    for( d in generatorData){
        println(" ${d.position} - ${d.amount}")
    }
    println()
    val adapter = DataDisplayAdapter(DataDisplay())
    val convertData = adapter.convertData(generatorData)
    println("WITH ADAPTER")
    for( d  in convertData){
        println(" ${d.index} - ${d.data}")
    }
}