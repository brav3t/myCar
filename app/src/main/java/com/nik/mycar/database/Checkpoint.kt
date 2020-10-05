package com.nik.mycar.database

import androidx.room.*
import java.util.*

@Entity(
    tableName = "checkpoints_table",
    foreignKeys = [
        ForeignKey(entity = Car::class, parentColumns = ["id"], childColumns = ["car_id"])
    ],
    indices = [Index("car_id")]
)
data class Checkpoint(

    @ColumnInfo(name = "car_id")
    val carId: Long,

    @ColumnInfo(name = "date")
    var date: Calendar = Calendar.getInstance(),

    @ColumnInfo(name = "mileage")
    var mileage: Int,

    @ColumnInfo(name = "avg_consumption")
    var avg_consumption: Double,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var checkpointId: Long
)
