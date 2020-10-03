package com.nik.mycar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "services_table")
data class Service(

    @PrimaryKey(autoGenerate = true)
    var serviceId: Long,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "cost")
    var cost: Double
)
