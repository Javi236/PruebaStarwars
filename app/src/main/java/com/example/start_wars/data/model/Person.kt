package com.example.start_wars.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.start_wars.data.model.Films
import kotlinx.parcelize.Parcelize

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Films::class,
            parentColumns = ["episode_id"],
            childColumns = ["filmId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index(value = ["filmId"])]
)
@Parcelize
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val imgStarWars: Int,
    val filmId: Int
) : Parcelable
