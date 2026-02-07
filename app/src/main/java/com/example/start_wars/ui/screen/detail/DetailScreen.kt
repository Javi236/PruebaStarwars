package com.example.start_wars.ui.screen.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.start_wars.data.model.Films
import com.example.start_wars.composables.DetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier,
    film: Films?,
    viewModel: DetailViewModel,
    goToBack: () -> Unit,
) {
    val state = viewModel.state
    val isEdit = film != null

    LaunchedEffect(film) {
        if (film == null) {
            viewModel.iniciar()
        } else {
            viewModel.cargar(film)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.guardar(goToBack) }
            ) {
                Icon(
                    imageVector = if (isEdit) Icons.Default.Save else Icons.Default.Done,
                    contentDescription = "Guardar"
                )
            }
        }
    ) { padding ->

        DetailContent(
            modifier = modifier.padding(padding),
            editar = isEdit,
            title = state.title,
            episodeId = state.episode_id,
            openingCrawl = state.opening_crawl,
            director = state.director,
            producer = state.producer,
            releaseDate = state.release_date,
            species = state.species,
            starships = state.starships,
            vehicles = state.vehicles,
            characters = state.characters,
            planets = state.planets,
            url = state.url,
            created = state.created,
            edited = state.edited,
            onTitleChange = viewModel::onTitleChange,
            onEpisodeIdChange = viewModel::onEpisodeIdChange,
            onOpeningCrawlChange = viewModel::onOpeningCrawlChange,
            onDirectorChange = viewModel::onDirectorChange,
            onProducerChange = viewModel::onProducerChange,
            onReleaseDateChange = viewModel::onReleaseDateChange,
            onSpeciesChange = viewModel::onSpeciesChange,
            onStarshipsChange = viewModel::onStarshipsChange,
            onVehiclesChange = viewModel::onVehiclesChange,
            onCharactersChange = viewModel::onCharactersChange,
            onPlanetsChange = viewModel::onPlanetsChange,
            onUrlChange = viewModel::onUrlChange,
            onCreatedChange = viewModel::onCreatedChange,
            onEditedChange = viewModel::onEditedChange
        )
    }
}

