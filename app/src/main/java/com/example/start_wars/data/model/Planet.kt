package com.example.start_wars.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "Planets")
@Parcelize
data class Planet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val climate: String,
    val terrain: String,
    val population: String,
    val gravity: String,
    val diameter: String
): Parcelable{

}