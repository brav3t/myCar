package com.nik.mycar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "fuelling_table")
data class Fuelling(

    @PrimaryKey(autoGenerate = true)
    var fuellingId: Long,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "amount")
    var amount: Double,

    @ColumnInfo(name = "cost")
    var cost: Double
)
