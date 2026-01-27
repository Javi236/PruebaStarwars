package com.example.start_wars.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "film_planet",
    //1.Se establece las claves primarias formadas por los dos id de las tablas
    primaryKeys = ["filmId", "planetId"],
    foreignKeys = [
        ForeignKey(
            entity = Films::class,
            parentColumns = ["episode_id"],
            childColumns = ["filmId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Planet::class,
            parentColumns = ["id"],
            childColumns = ["planetId"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)

data class FilmPlanetEntity(
    @ColumnInfo(name = "filmId")
    val filmId: Int,
    @ColumnInfo(name = "planetId")
    val planetId: Int
)