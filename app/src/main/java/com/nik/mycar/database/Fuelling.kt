package com.nik.mycar.database

import androidx.room.*
import java.util.*

@Entity(
    tableName = "fuelling_table",
    foreignKeys = [
        ForeignKey(entity = Car::class, parentColumns = ["id"], childColumns = ["car_id"])
    ],
    indices = [Index("car_id")]
)
data class Fuelling(

    @ColumnInfo(name = "car_id")
    val carId: Long,

    @ColumnInfo(name = "date")
    var date: Calendar = Calendar.getInstance(),

    @ColumnInfo(name = "amount")
    var amount: Double,

    @ColumnInfo(name = "cost")
    var cost: Double,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var fuellingId: Long,
)
