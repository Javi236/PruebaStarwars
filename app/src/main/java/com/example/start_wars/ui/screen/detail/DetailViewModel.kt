package com.example.start_wars.ui.screen.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.start_wars.data.Films
import com.example.start_wars.data.model.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.time.LocalDateTime


//Mismo viewModel tanto para la accion de añadir que de editar

@HiltViewModel
open class DetailViewModel @Inject constructor(
    private val repository: FilmsRepository
) : ViewModel() {

    var state = mutableStateOf(DetailState())
        private set

    private var editingFilm: Films? = null

    fun iniciar() {
        editingFilm = null
        state.value = DetailState()
    }

    fun cargar(film: Films) {
        editingFilm = film
        state.value = state.value.copy(
            title = film.title,
            episode_id = film.episode_id,
            opening_crawl = film.opening_crawl,
            director = film.director,
            producer = film.producer,
            release_date = film.release_date,
            era = film.era,
            rating = film.rating,
            is_original_trilogy = film.is_original_trilogy,
            species = film.species,
            starships = film.starships,
            vehicles = film.vehicles,
            characters = film.characters,
            planets = film.planets,
            url = film.url,
            created = film.created,
            edited = film.edited
        )
    }

    fun onTitleChange(value: String) {
        state.value = state.value.copy(title = value)
    }

    fun onEpisodeIdChange(value: Int) {
        state.value = state.value.copy(episode_id = value)
    }

    fun onOpeningCrawlChange(value: String) {
        state.value = state.value.copy(opening_crawl = value)
    }

    fun onDirectorChange(value: String) {
        state.value = state.value.copy(director = value)
    }

    fun onProducerChange(value: String) {
        state.value = state.value.copy(producer = value)
    }

    fun onReleaseDateChange(value: String) {
        state.value = state.value.copy(release_date = value)
    }

    fun onEraChange(value: String) {
        state.value = state.value.copy(era = value)
    }

    fun onRatingChange(value: String) {
        state.value = state.value.copy(rating = value)
    }

    fun onIsOriginalTrilogyChange(value: Boolean) {
        state.value = state.value.copy(is_original_trilogy = value)
    }

    fun onSpeciesChange(value: List<String>) {
        state.value = state.value.copy(species = value)
    }

    fun onStarshipsChange(value: List<String>) {
        state.value = state.value.copy(starships = value)
    }

    fun onVehiclesChange(value: List<String>) {
        state.value = state.value.copy(vehicles = value)
    }

    fun onCharactersChange(value: List<String>) {
        state.value = state.value.copy(characters = value)
    }

    fun onPlanetsChange(value: List<String>) {
        state.value = state.value.copy(planets = value)
    }

    fun onUrlChange(value: String) {
        state.value = state.value.copy(url = value)
    }

    fun onCreatedChange(value: String) {
        state.value = state.value.copy(created = value)
    }

    fun onEditedChange(value: String) {
        state.value = state.value.copy(edited = value)
    }

    fun guardar(goToBack: () -> Unit) {
        viewModelScope.launch {
            // si es null significa crear película
            if (editingFilm == null) {
                //Creamos una fecha para que tenga el mismo valor en los dos campos que son de fecha
                val now = LocalDateTime.now().toString()

                val newFilm = Films(
                    title = state.value.title,
                    episode_id = state.value.episode_id,
                    opening_crawl = state.value.opening_crawl,
                    director = state.value.director,
                    producer = state.value.producer,
                    release_date = state.value.release_date,
                    era = state.value.era,
                    rating = state.value.rating,
                    is_original_trilogy = state.value.is_original_trilogy,
                    species = state.value.species,
                    starships = state.value.starships,
                    vehicles = state.value.vehicles,
                    characters = state.value.characters,
                    planets = state.value.planets,
                    url = state.value.url,
                    created = now,
                    edited = now
                )

                repository.add(newFilm)
                goToBack()

            } else {
                // edito si no es null
                val now = LocalDateTime.now().toString()

                val updated = editingFilm!!.copy(
                    title = state.value.title,
                    episode_id = state.value.episode_id,
                    opening_crawl = state.value.opening_crawl,
                    director = state.value.director,
                    producer = state.value.producer,
                    release_date = state.value.release_date,
                    era = state.value.era,
                    rating = state.value.rating,
                    is_original_trilogy = state.value.is_original_trilogy,
                    species = state.value.species,
                    starships = state.value.starships,
                    vehicles = state.value.vehicles,
                    characters = state.value.characters,
                    planets = state.value.planets,
                    url = state.value.url,
                    created = state.value.created,
                    edited = now
                )

                repository.update(editingFilm!!.title, updated)
                goToBack()
            }
        }
    }
}
