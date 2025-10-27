package com.example.start_wars.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.start_wars.R
import com.example.start_wars.ui.clases.Films


@Composable
fun CreacionPelicula(modifier: Modifier = Modifier) {

    var film by remember { mutableStateOf(Films()) }

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderBoxSign()

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

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

            Text(
                text = stringResource(R.string.tittle),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.title,
                onValueChange = { film = film.copy(title = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.tittle)) }
            )

            Text(
                text = stringResource(R.string.episode_id),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.episode_id.toString(),
                onValueChange = { film = film.copy(episode_id = it.toIntOrNull() ?: 0) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.episode_id)) }
            )

            Text(
                text = stringResource(R.string.opening_crawl),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.opening_crawl,
                onValueChange = { film = film.copy(opening_crawl = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.opening_crawl)) },
                maxLines = 4
            )

            Text(
                text = stringResource(R.string.director),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.director,
                onValueChange = { film = film.copy(director = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.director_es)) }
            )

            Text(
                text = stringResource(R.string.producer),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.producer,
                onValueChange = { film = film.copy(producer = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.producer_es)) }
            )

            Text(
                text = stringResource(R.string.release_date),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.release_date,
                onValueChange = { film = film.copy(release_date = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Fecha de estreno") }
            )


            //HACER LOS CAMPOS COMO LOS DE ARRIBA!!!!
            Text(
                text = "Especies",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.species.joinToString(","),
                onValueChange = { film = film.copy(species = it.split(",").map { s -> s.trim() }) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("URLs separadas por coma") }
            )

            Text(
                text = "Naves espaciales",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.starships.joinToString(","),
                onValueChange = {
                    film = film.copy(starships = it.split(",").map { s -> s.trim() })
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("URLs separadas por coma") }
            )

            Text(
                text = "Vehículos",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.vehicles.joinToString(","),
                onValueChange = {
                    film = film.copy(vehicles = it.split(",").map { s -> s.trim() })
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("URLs separadas por coma") }
            )

            Text(
                text = "Personajes",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.characters.joinToString(","),
                onValueChange = {
                    film = film.copy(characters = it.split(",").map { s -> s.trim() })
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("URLs separadas por coma") }
            )

            Text(
                text = "Planetas",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.planets.joinToString(","),
                onValueChange = { film = film.copy(planets = it.split(",").map { s -> s.trim() }) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("URLs separadas por coma") }
            )

            Text(
                text = "URL recurso",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.url,
                onValueChange = { film = film.copy(url = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("URL recurso") }
            )

            Text(
                text = "Creado",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.created,
                onValueChange = { film = film.copy(created = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Creado") }
            )

            Text(
                text = "Editado",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.edited,
                onValueChange = { film = film.copy(edited = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Editado") }
            )
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear película")
            }

            Spacer(modifier = Modifier.height(60.dp))

        }
    }
}


@Composable
fun HeaderBoxSign() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.star_wars),
            contentDescription = stringResource(R.string.star_war_desc),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .background(Color(0xAA000000))
                .matchParentSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreacionPelicula() {
    // Si tienes theme propio, úsalo aquí como haces en SignUp()
    CreacionPelicula()
}


