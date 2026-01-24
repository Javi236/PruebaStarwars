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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.start_wars.R

// Mismo contenido para añadir y editar
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

    Column(
        modifier = modifier.fillMaxSize() // ✅ ahora sí usamos el modifier recibido
    ) {
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

            CampoTexto("Título", title, onTitleChange)

            CampoTexto(
                label = "Episodio",
                valor = episodeId.toString(),
                onChange = { onEpisodeIdChange(it.toIntOrNull() ?: 0) }
            )

            CampoTexto("Opening Crawl", openingCrawl, onOpeningCrawlChange)
            CampoTexto("Director", director, onDirectorChange)
            CampoTexto("Productor", producer, onProducerChange)
            CampoTexto("Fecha de estreno", releaseDate, onReleaseDateChange)
            CampoTexto("Especies", species, onSpeciesChange)
            CampoTexto("Naves", starships, onStarshipsChange)
            CampoTexto("Vehículos", vehicles, onVehiclesChange)
            CampoTexto("Personajes", characters, onCharactersChange)
            CampoTexto("Planetas", planets, onPlanetsChange)
            CampoTexto("URL", url, onUrlChange)

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

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    DetailContent(
        editar = false,
        title = "A New Hope",
        episodeId = 4,
        openingCrawl = "It is a period of civil war...",
        director = "George Lucas",
        producer = "Gary Kurtz",
        releaseDate = "1977-05-25",
        species = "Human, Droid",
        starships = "X-Wing, TIE Fighter",
        vehicles = "Sand Crawler",
        characters = "Luke Skywalker, Leia Organa",
        planets = "Tatooine, Yavin 4",
        url = "https://swapi.dev/api/films/1/",
        created = "2026-01-01T10:00:00",
        edited = "2026-01-05T12:00:00",
        onTitleChange = {},
        onEpisodeIdChange = {},
        onOpeningCrawlChange = {},
        onDirectorChange = {},
        onProducerChange = {},
        onReleaseDateChange = {},
        onSpeciesChange = {},
        onStarshipsChange = {},
        onVehiclesChange = {},
        onCharactersChange = {},
        onPlanetsChange = {},
        onUrlChange = {},
        onCreatedChange = {},
        onEditedChange = {}
    )
}
