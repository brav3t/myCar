package com.nik.mycar.data

import androidx.room.*
import java.util.*

@Entity(
    tableName = "service_table",
    foreignKeys = [
        ForeignKey(entity = Car::class, parentColumns = ["id"], childColumns = ["car_id"])
    ],
    indices = [Index("car_id")]
)
data class Service(

    @ColumnInfo(name = "car_id")
    val carId: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "cost")
    val cost: Double,

    @ColumnInfo(name = "date")
    val date: Calendar = Calendar.getInstance(),

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val serviceId: Long = 0
)
