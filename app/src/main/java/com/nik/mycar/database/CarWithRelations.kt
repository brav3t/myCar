package com.nik.mycar.database

import androidx.room.Embedded
import androidx.room.Relation

data class CarWithRelations (

    @Embedded val car: Car,
    @Relation(
        parentColumn = "carId",
        entityColumn = "checkpointId"
    )
    val carStates: List<Checkpoint>,

    @Relation(
        entity = Fuelling::class,
        parentColumn = "carId",
        entityColumn = "fuellingId"
    )
    val carFuelling: List<Fuelling>,

    @Relation(
        entity = Service::class,
        parentColumn = "carId",
        entityColumn = "serviceId"
    )
    val carServices: List<Service>
)
