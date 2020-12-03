package com.nik.mycar.data

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
    val carId: String,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "cost")
    val cost: Double,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val fuellingId: Long = 0,

    @ColumnInfo(name = "date")
    val date: Calendar = Calendar.getInstance()
)
