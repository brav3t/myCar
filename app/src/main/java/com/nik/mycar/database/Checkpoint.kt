package com.nik.mycar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "checkpoints_table")
data class Checkpoint(

    @PrimaryKey(autoGenerate = true)
    var checkpointId: Long,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "mileage")
    var mileage: Int,

    @ColumnInfo(name = "avg_consumption")
    var avg_consumption: Double
)
