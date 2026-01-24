package com.example.start_wars.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.start_wars.data.model.Person
import com.example.start_wars.data.model.PersonWithFilms
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert
    suspend fun insert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM person")
    fun getAll(): Flow<List<Person>>

    @Query("SELECT EXISTS (SELECT * FROM person WHERE person.name = :name)")
    suspend fun exists(name: String): Boolean

    //Para contar cuantos personajes hay
    @Query("SELECT COUNT(*) FROM person")
    suspend fun count(): Int

    @Transaction
    @Query("SELECT * FROM person WHERE id = :id")
    suspend fun getPersonWithFilms(id:Int): PersonWithFilms
}