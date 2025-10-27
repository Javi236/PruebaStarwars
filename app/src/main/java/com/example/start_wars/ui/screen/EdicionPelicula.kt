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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdicionPelicula(modifier: Modifier = Modifier) {

    var film by remember { mutableStateOf(Films()) }
    var isCreating by remember { mutableStateOf(true) }

    val opciones = listOf("Opción 1", "Opción 2", "Opción 3")

    var expanded by remember { mutableStateOf(false) }
    var seleccion by remember { mutableStateOf(opciones.first()) }

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderBoxEdicion()

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
                text = stringResource(R.string.edicion_pelicula),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = stringResource(R.string.subtexto_edicion_pelicula),
                style = MaterialTheme.typography.titleMedium
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded } // alternar abrir/cerrar
            ) {
                OutlinedTextField(
                    value = seleccion,
                    onValueChange = {},
                    readOnly = true, // el usuario no escribe, solo elige
                    label = { Text("Selecciona una opción") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                    },
                    modifier = Modifier
                        .menuAnchor() // ancla el menú al TextField
                        .fillMaxWidth()
                )

                //Menú de opciones
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    opciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                seleccion = opcion   // actualiza la selección
                                expanded = false     // cierra el menú
                            }
                        )
                    }
                }

            }
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
                label = { Text(stringResource(R.string.episode_id)) },
                enabled = isCreating  // editable solo si aún no se ha puesto
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
                label = { Text(stringResource(R.string.release_date)) }
            )

            Text(
                text = stringResource(R.string.species),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.species.joinToString(","),
                onValueChange = {
                    film = film.copy(species = it.split(",").map { s -> s.trim() })
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.species)) }
            )

            Text(
                text = stringResource(R.string.starships),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.starships.joinToString(","),
                onValueChange = {
                    film = film.copy(starships = it.split(",").map { s -> s.trim() })
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.starships)) }
            )

            Text(
                text = stringResource(R.string.vehicles),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.vehicles.joinToString(","),
                onValueChange = {
                    film = film.copy(vehicles = it.split(",").map { s -> s.trim() })
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.vehicles)) }
            )

            Text(
                text = stringResource(R.string.characters),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.characters.joinToString(","),
                onValueChange = {
                    film = film.copy(characters = it.split(",").map { s -> s.trim() })
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.characters)) }
            )

            Text(
                text = stringResource(R.string.planets),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.planets.joinToString(","),
                onValueChange = {
                    film = film.copy(planets = it.split(",").map { s -> s.trim() })
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.planets)) }
            )

            Text(
                text = stringResource(R.string.url),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.url,
                onValueChange = { film = film.copy(url = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.url)) },
                enabled = isCreating  // editable solo si aún no se ha puesto
            )

            Text(
                text = stringResource(R.string.created),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.created,
                onValueChange = { film = film.copy(created = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.created))},
                enabled = isCreating  // editable solo si aún no se ha puesto
            )

            Text(
                text = stringResource(R.string.edited),
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = film.edited,
                onValueChange = { film = film.copy(edited = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.edited)) }
            )

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A84FF),   // fondo del botón
                    contentColor = Color(0xFFFFD700)
                ),
                modifier = Modifier.fillMaxWidth().padding(24.dp)
            ) {
                Text(stringResource(R.string.editar_pelicula))
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}


@Composable
fun HeaderBoxEdicion() {
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
fun PreviewEdicionPelicula() {
    // Si tienes theme propio, úsalo aquí como haces en SignUp()
    EdicionPelicula()
}


