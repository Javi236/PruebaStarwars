package com.example.start_wars.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// Clase película
@Parcelize
@Entity(tableName = "films")
data class Films(
    var title: String = "",
    @PrimaryKey
    var episode_id: Int = 0,
    var opening_crawl: String = "",
    var director: String = "",
    var producer: String = "",
    var release_date: String = "",
    var era: String = "",
    var rating: String = "",
    var is_original_trilogy: Boolean = false,
    var species: String = "",
    var starships: String = "",
    var vehicles: String = "",
    var characters: String = "",
    var planets: String = "",
    var url: String = "",
    var created: String = "",
    var edited: String = ""
) : Parcelable
