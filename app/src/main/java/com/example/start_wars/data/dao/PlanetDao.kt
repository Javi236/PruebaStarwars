package com.example.start_wars.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.start_wars.data.model.Planet
import kotlinx.coroutines.flow.Flow


@Dao
interface PlanetDao {

    @Insert
    fun insert (planet: Planet)

    @Delete
    fun delete (planet: Planet)

    @Update
    fun update (planet: Planet)

    @Query ("SELECT * FROM planets")
    fun getAll(): Flow<List<Planet>>

    @Query ("SELECT EXISTS (SELECT * FROM planets WHERE planets.id = :id)")
    fun exists(id: Int): Boolean
}