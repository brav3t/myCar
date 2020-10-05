package com.nik.mycar.database

import androidx.room.*
import java.util.*

@Entity(
    tableName = "services_table",
    foreignKeys = [
        ForeignKey(entity = Car::class, parentColumns = ["id"], childColumns = ["car_id"])
    ],
    indices = [Index("car_id")]
)
data class Service(

    @ColumnInfo(name = "car_id")
    val carId: Long,

    @ColumnInfo(name = "date")
    var date: Calendar = Calendar.getInstance(),

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "cost")
    var cost: Double,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var serviceId: Long
)
