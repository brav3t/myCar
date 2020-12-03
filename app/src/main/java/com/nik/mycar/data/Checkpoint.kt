package com.nik.mycar.data

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
    val carId: String,

    @ColumnInfo(name = "checkpoint")
    val checkpoint: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val checkpointId: Long = 0,

    @ColumnInfo(name = "date")
    val date: Calendar = Calendar.getInstance()
)
