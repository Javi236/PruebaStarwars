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
import com.example.start_wars.data.Films
import com.example.start_wars.composables.DetailContent

//Misma pantalla tanto para la accion añadir que editar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier,
    film: Films?,
    viewModel: DetailViewModel,
    goToBack: () -> Unit
) {

    val state = viewModel.state.value
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
            species = state.species.joinToString(","),
            starships = state.starships.joinToString(","),
            vehicles = state.vehicles.joinToString(","),
            characters = state.characters.joinToString(","),
            planets = state.planets.joinToString(","),
            url = state.url,
            created = state.created,
            edited = state.edited,
            onTitleChange = viewModel::onTitleChange,
            onEpisodeIdChange = viewModel::onEpisodeIdChange,
            onOpeningCrawlChange = viewModel::onOpeningCrawlChange,
            onDirectorChange = viewModel::onDirectorChange,
            onProducerChange = viewModel::onProducerChange,
            onReleaseDateChange = viewModel::onReleaseDateChange,
            //Para estas acciones he tenido que consultar la ia, porque me estaban dando fallos
            onSpeciesChange = { text ->
                viewModel.onSpeciesChange(
                    text.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                )
            },
            onStarshipsChange = { text ->
                viewModel.onStarshipsChange(
                    text.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                )
            },
            onVehiclesChange = { text ->
                viewModel.onVehiclesChange(
                    text.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                )
            },
            onCharactersChange = { text ->
                viewModel.onCharactersChange(
                    text.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                )
            },
            onPlanetsChange = { text ->
                viewModel.onPlanetsChange(
                    text.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                )
            },
            onUrlChange = viewModel::onUrlChange,
            onCreatedChange = viewModel::onCreatedChange,
            onEditedChange = viewModel::onEditedChange
        )
    }
}
