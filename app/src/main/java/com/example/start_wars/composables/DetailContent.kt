package com.example.start_wars.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.start_wars.R
//Hacemo un mismo archivo tanto para añadir como para editar y dependiendo de la accion se muestran unos mensajes u otros
@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    editar: Boolean,
    title: String,
    episodeId: Int,
    openingCrawl: String,
    director: String,
    producer: String,
    releaseDate: String,
    species: String,
    starships: String,
    vehicles: String,
    characters: String,
    planets: String,
    url: String,
    created: String,
    edited: String,
    onTitleChange: (String) -> Unit,
    onEpisodeIdChange: (Int) -> Unit,
    onOpeningCrawlChange: (String) -> Unit,
    onDirectorChange: (String) -> Unit,
    onProducerChange: (String) -> Unit,
    onReleaseDateChange: (String) -> Unit,
    onSpeciesChange: (String) -> Unit,
    onStarshipsChange: (String) -> Unit,
    onVehiclesChange: (String) -> Unit,
    onCharactersChange: (String) -> Unit,
    onPlanetsChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onCreatedChange: (String) -> Unit,
    onEditedChange: (String) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderBox()

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            //Aqui dependiendo de lo que sea mostramos una cosa u otra
            if (!editar) {
                Text(
                    text = stringResource(R.string.creacion_pelicula),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = stringResource(R.string.subtexto_creacion_pelicula),
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                Text(
                    text = stringResource(R.string.editar_pelicula),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

            }

            CampoTexto(
                label = "Título",
                valor = title,
                onChange = onTitleChange
            )

            CampoTexto(
                label = "Episodio",
                valor = episodeId.toString(),
                onChange = { onEpisodeIdChange(it.toIntOrNull() ?: 0) }
            )

            CampoTexto(
                label = "Opening Crawl",
                valor = openingCrawl,
                onChange = onOpeningCrawlChange
            )

            CampoTexto(
                label = "Director",
                valor = director,
                onChange = onDirectorChange
            )

            CampoTexto(
                label = "Productor",
                valor = producer,
                onChange = onProducerChange
            )

            CampoTexto(
                label = "Fecha de estreno",
                valor = releaseDate,
                onChange = onReleaseDateChange
            )

            CampoTexto(
                label = "Especies",
                valor = species,
                onChange = onSpeciesChange
            )

            CampoTexto(
                label = "Naves",
                valor = starships,
                onChange = onStarshipsChange
            )

            CampoTexto(
                label = "Vehículos",
                valor = vehicles,
                onChange = onVehiclesChange
            )

            CampoTexto(
                label = "Personajes",
                valor = characters,
                onChange = onCharactersChange
            )

            CampoTexto(
                label = "Planeta",
                valor = planets,
                onChange = onPlanetsChange
            )

            CampoTexto(
                label = "URL",
                valor = url,
                onChange = onUrlChange
            )

            CampoTexto(
                label = "Creado",
                valor = created,
                onChange = onCreatedChange,
                enabled = false
            )

            CampoTexto(
                label = "Editado",
                valor = edited,
                onChange = onEditedChange,
                enabled = false
            )

            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}
