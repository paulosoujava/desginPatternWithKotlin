package com.example.desginpatterkotlin.creational.abstractFactory

interface DataSource
class DatabaseDataSource : DataSource
class NetworkDataSource : DataSource
abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T : DataSource> createFactory(): DataSourceFactory =
            when (T::class) {
                DatabaseDataSource::class -> DatabaseFactory()
                NetworkDataSource::class -> NetworkFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class NetworkFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDataSource()
}

class DatabaseFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDataSource()
}

//other
interface Unit
interface Vehicle : Unit
interface Infantry : Unit
class Rifleman : Infantry
class RocketSoldier : Infantry
enum class InfantryUnits {
    RIFLEMEN,
    ROCKET_SOLDIER
}
interface Building<in UnitType, out ProducedUnit>
        where UnitType : Enum<*>, ProducedUnit : Unit {
    fun build(type: UnitType): ProducedUnit
}

class APC : Vehicle
class Tank : Vehicle
enum class VehicleUnits {
    APC,
    TANK
}
interface HQ {
    fun buildBarracks(): Building<InfantryUnits, Infantry>
    fun buildVehicleFactory(): Building<VehicleUnits, Vehicle>
}
class Test: HQ {
    val buildings = mutableListOf<Building<*, Unit>>()
    override fun buildBarracks(): Building<InfantryUnits, Infantry> {
        val b = object : Building<InfantryUnits, Infantry> {
            override fun build(type: InfantryUnits): Infantry {
                return when (type) {
                    InfantryUnits.RIFLEMEN -> Rifleman()
                    InfantryUnits.ROCKET_SOLDIER -> RocketSoldier()
                }
            }
        }
        buildings.add(b)
        return b
    }

    override fun buildVehicleFactory(): Building<VehicleUnits, Vehicle> {
        val vf = object : Building<VehicleUnits, Vehicle> {
            override fun build(type: VehicleUnits) = when (type) {
                VehicleUnits.APC -> APC()
                VehicleUnits.TANK -> Tank()
            } }
        buildings.add(vf)
        return vf }
}

fun main() {
    val hq = Test()
    val barracks1 = hq.buildBarracks()
    val barracks2 = hq.buildBarracks()
    val vehicleFactory1 = hq.buildVehicleFactory()
    val units = listOf(
        barracks1.build(InfantryUnits.RIFLEMEN),
        barracks2.build(InfantryUnits.ROCKET_SOLDIER),
        barracks2.build(InfantryUnits.ROCKET_SOLDIER),
        vehicleFactory1.build(VehicleUnits.TANK),
        vehicleFactory1.build(VehicleUnits.APC),
        vehicleFactory1.build(VehicleUnits.APC)
    )

}