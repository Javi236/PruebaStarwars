package com.example.start_wars.ui.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.start_wars.data.model.Films
import com.example.start_wars.data.repository.FilmsRepository
import com.example.start_wars.network.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: FilmsRepository
) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    private var editingFilm: Films? = null

    fun iniciar() {
        editingFilm = null
        state = DetailState()
    }

    fun cargar(film: Films) {
        editingFilm = film
        state = state.copy(
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

    // --- Funciones onChange ---
    fun onTitleChange(value: String) {
        state = state.copy(title = value)
    }

    fun onEpisodeIdChange(value: Int) {
        state = state.copy(episode_id = value)
    }

    fun onOpeningCrawlChange(value: String) {
        state = state.copy(opening_crawl = value)
    }

    fun onDirectorChange(value: String) {
        state = state.copy(director = value)
    }

    fun onProducerChange(value: String) {
        state = state.copy(producer = value)
    }

    fun onReleaseDateChange(value: String) {
        state = state.copy(release_date = value)
    }

    fun onEraChange(value: String) {
        state = state.copy(era = value)
    }

    fun onRatingChange(value: String) {
        state = state.copy(rating = value)
    }

    fun onIsOriginalTrilogyChange(value: Boolean) {
        state = state.copy(is_original_trilogy = value)
    }

    fun onSpeciesChange(value: String) {
        state = state.copy(species = value)
    }

    fun onStarshipsChange(value: String) {
        state = state.copy(starships = value)
    }

    fun onVehiclesChange(value: String) {
        state = state.copy(vehicles = value)
    }

    fun onCharactersChange(value: String) {
        state = state.copy(characters = value)
    }

    fun onPlanetsChange(value: String) {
        state = state.copy(planets = value)
    }

    fun onUrlChange(value: String) {
        state = state.copy(url = value)
    }

    fun onCreatedChange(value: String) {
        state = state.copy(created = value)
    }

    fun onEditedChange(value: String) {
        state = state.copy(edited = value)
    }

    fun guardar(goToBack: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val now = LocalDateTime.now().toString()

            if (editingFilm == null) {
                // Crear película
                val newFilm = Films(
                    title = state.title,
                    episode_id = state.episode_id,
                    opening_crawl = state.opening_crawl,
                    director = state.director,
                    producer = state.producer,
                    release_date = state.release_date,
                    era = state.era,
                    rating = state.rating,
                    is_original_trilogy = state.is_original_trilogy,
                    species = state.species,
                    starships = state.starships,
                    vehicles = state.vehicles,
                    characters = state.characters,
                    planets = state.planets,
                    url = state.url,
                    created = now,
                    edited = now
                )

                repository.addFilm(newFilm)

            } else {
                // Editar película existente
                val updated = editingFilm!!.copy(
                    title = state.title,
                    episode_id = state.episode_id,
                    opening_crawl = state.opening_crawl,
                    director = state.director,
                    producer = state.producer,
                    release_date = state.release_date,
                    era = state.era,
                    rating = state.rating,
                    is_original_trilogy = state.is_original_trilogy,
                    species = state.species,
                    starships = state.starships,
                    vehicles = state.vehicles,
                    characters = state.characters,
                    planets = state.planets,
                    url = state.url,
                    created = state.created,
                    edited = now
                )

                repository.updateFilm(updated)
            }

            // Navegación en hilo principal
            launch(Dispatchers.Main) {
                goToBack()
            }

        }
    }
}
