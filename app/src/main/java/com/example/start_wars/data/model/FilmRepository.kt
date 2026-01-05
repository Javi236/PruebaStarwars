package com.example.start_wars.data.model

import com.example.start_wars.data.FilmException
import com.example.start_wars.data.Films
import com.example.start_wars.network.BaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

//Repositorio de ejemplo para mostar como funciona el listar
@Singleton
class FilmsRepository @Inject constructor() {
    //dateset contiene todas las cuentas (account) de la aplicacion
    private var dataset = mutableListOf<Films>()
    var films = MutableStateFlow<List<Films>>(emptyList())
        private set

    init {
        inciar()
        notifyChange()
    }

    private fun inciar() {
        dataset.add(
            Films(
                title = "A New Hope",
                episode_id = 4,
                opening_crawl = "It is a period of civil war...",
                director = "George Lucas",
                producer = "Gary Kurtz, Rick McCallum",
                release_date = "1977-05-25",
                era = "Imperial Era",
                rating = "8.6",
                is_original_trilogy = true,
                species = listOf("Human", "Droid"),
                starships = listOf("X-Wing", "TIE Fighter"),
                vehicles = listOf("Sand Crawler"),
                characters = listOf("Luke Skywalker", "Leia Organa"),
                planets = listOf("Tatooine", "Yavin 4"),
                url = "https://swapi.dev/api/films/1/"
            ),
        )
        dataset.add(
            Films(
                title = "The Empire Strikes Back",
                episode_id = 5,
                opening_crawl = "It is a dark time for the Rebellion...",
                director = "Irvin Kershner",
                producer = "Gary Kurtz, Rick McCallum",
                release_date = "1980-05-17",
                era = "Imperial Era",
                rating = "9.0",
                is_original_trilogy = true,
                species = listOf("Human", "Droid"),
                starships = listOf("Millennium Falcon"),
                vehicles = listOf("AT-AT"),
                characters = listOf("Han Solo", "Darth Vader"),
                planets = listOf("Hoth", "Dagobah"),
                url = "https://swapi.dev/api/films/2/"
            ),
        )

        dataset.add(
            Films(
                title = "The Phantom Menace",
                episode_id = 1,
                opening_crawl = "Turmoil has engulfed the Galactic Republic...",
                director = "George Lucas",
                producer = "Rick McCallum",
                release_date = "1999-05-19",
                era = "Republic Era",
                rating = "6.5",
                is_original_trilogy = false,
                species = listOf("Gungan", "Human", "Droid"),
                starships = listOf("Naboo Starfighter"),
                vehicles = listOf("STAP"),
                characters = listOf("Obi-Wan Kenobi", "Qui-Gon Jinn"),
                planets = listOf("Naboo", "Tatooine"),
                url = "https://swapi.dev/api/films/4/"
            )
        )
    }


    fun delete(film: Films): BaseResult<Films> {

        val existing = dataset.firstOrNull { it.title == film.title }

        if (existing == null) {
            return BaseResult.Error(
                FilmException.NotFound("No existe la cuenta a eliminar")
            )
        }

        dataset.remove(existing)
        notifyChange()

        return BaseResult.Success(existing)
    }

    fun notifyChange() {
        films.value = dataset.toList()
    }

    fun update(tituloOriginal: String, updated: Films): BaseResult<Films> {

        val existing = dataset.firstOrNull { it.title == tituloOriginal }

        if (existing == null) {
            return BaseResult.Error(
                FilmException.NotFound("No existe la pelicula")
            )
        }

        val index = dataset.indexOf(existing)
        dataset[index] = updated

        notifyChange()
        return BaseResult.Success(updated)
    }


    fun add(film: Films): BaseResult<Films> {
        if (!dataset.contains(film)) {
            dataset.add(film)
            notifyChange()
            return BaseResult.Success(film)
        } else return BaseResult.Error(FilmException.Exists(film.title))
    }
}

