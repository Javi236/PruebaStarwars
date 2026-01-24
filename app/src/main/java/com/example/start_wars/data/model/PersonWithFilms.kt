package com.example.start_wars.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithFilms(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "filmId",       // en Person
        entityColumn = "episode_id"    // en Films
    )
    val film: Films
)
