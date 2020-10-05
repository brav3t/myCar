package com.nik.mycar.database

import androidx.room.Embedded
import androidx.room.Relation

data class CarWithRelations (

    @Embedded val car: Car,
    @Relation(
        parentColumn = "id",
        entityColumn = "car_id"
    )
    val carStates: List<Checkpoint>,

    @Relation(
        entity = Fuelling::class,
        parentColumn = "id",
        entityColumn = "car_id"
    )
    val carFuelling: List<Fuelling>,

    @Relation(
        entity = Service::class,
        parentColumn = "id",
        entityColumn = "car_id"
    )
    val carServices: List<Service>
)
