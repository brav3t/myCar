package com.nik.mycar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_table")
data class Car(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val carId: Long,

    @ColumnInfo(name = "name")
    val name: String,
)
