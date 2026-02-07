package com.example.start_wars.data.repository

import com.example.start_wars.data.dao.PlanetDao
import com.example.start_wars.data.model.Planet
import com.example.start_wars.network.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetRepository @Inject constructor(
    private  val planetDao: PlanetDao
) {

    fun getData(): Flow<List<Planet>> = planetDao.getAll()
    fun addPlanet(planet: Planet): BaseResult<Planet> {
        if (!planetDao.exists(planet.id)){
            planetDao.insert(planet)

            return BaseResult.Success(planet)
        }
        return BaseResult.Error(Exception("Este planeta ya existe"))
    }

    fun removePlanet(planet: Planet){
        planetDao.delete(planet)
    }

    fun updatePlanet(planet: Planet){
        planetDao.update(planet)
    }
}