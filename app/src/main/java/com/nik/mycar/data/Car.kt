package com.nik.mycar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_table")
data class Car(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val carId: String
)
