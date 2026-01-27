package com.example.start_wars.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FilmWithPlanet(
    @Embedded val films: Films,
    @Relation(
        parentColumn = "episode_id",
        entityColumn = "id",
        associateBy = Junction(FilmPlanetEntity::class, parentColumn = "filmId",
            entityColumn = "planetId")

    )
    val planet: List<Planet>
)