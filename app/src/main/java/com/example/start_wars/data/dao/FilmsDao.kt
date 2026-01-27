package com.example.start_wars.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.start_wars.data.model.FilmPlanetEntity
import com.example.start_wars.data.model.FilmWithPlanet
import com.example.start_wars.data.model.Films
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmsDao {

    @Insert
    fun insert(film: Films)

    @Delete
    fun delete(film: Films)

    @Update
    fun update(film: Films)

    @Query("SELECT * FROM films")
    fun getAll(): Flow<List<Films>>

    @Query("SELECT EXISTS (SELECT 1 FROM films WHERE episode_id = :episodeId)")
    fun exists(episodeId: Int): Boolean

    //1. Busca la pelicula
    //2. Busca los planetas relacionados en la peli
    @Transaction
    @Query("SELECT * FROM films WHERE episode_id = :episodeId")
    fun getFilmWithPlanet(episodeId: Int): FilmWithPlanet

    @Insert
    fun insertJoinFilmPlanet(join: FilmPlanetEntity)

}
