package com.example.start_wars.data.repository

import com.example.start_wars.data.dao.FilmsDao
import com.example.start_wars.data.model.Films
import com.example.start_wars.network.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmsRepository @Inject constructor(
    private val filmDao: FilmsDao
) {

    fun getData(): Flow<List<Films>> = filmDao.getAll()

    fun addFilm(film: Films): BaseResult<Films> {
        if (!filmDao.exists(film.episode_id)) {
            filmDao.insert(film)
            return BaseResult.Success(film)
        }
        return BaseResult.Error(Exception("Esta película ya existe"))
    }

    fun removeFilm(film: Films) {
        filmDao.delete(film)
    }

    fun updateFilm(film: Films) {
        filmDao.update(film)
    }
}
