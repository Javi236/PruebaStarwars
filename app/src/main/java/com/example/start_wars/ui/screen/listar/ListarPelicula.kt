package com.example.start_wars.ui.screen.listar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.start_wars.R
import com.example.start_wars.composables.CardStyle
import com.example.start_wars.composables.LocalCardStyle
import com.example.start_wars.data.Films
import com.example.start_wars.ui.screen.alertDialog.AlertDialogOkCancel
import kotlinx.coroutines.launch


//Clase encargada de hacer el listar, aqui no se eleva el estado ya que solo hace la funcion de listar

data class FilmsListEvents(
    val onDelete: (Films) -> Unit,
    val onEditFilm: (Films) -> Unit,
    val onAddFilm: () -> Unit,
)

@Composable
fun ListarPelicula(modifier: Modifier = Modifier, film: Films) {
    val style = LocalCardStyle.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = style.backgroundColor,
            contentColor = style.contentColor
        ),
        border = BorderStroke(style.borderWidth, style.borderColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val icon = if (film.is_original_trilogy) {
                Icons.Default.Settings
            } else {
                Icons.Default.Face
            }

            Icon(
                imageVector = icon,
                contentDescription = "Film Icon",
                modifier = Modifier.size(48.dp),
                tint = style.borderColor
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = film.title,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Episodio: ${film.episode_id}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Director: ${film.director}",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Productor: ${film.producer}",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Fecha de estreno: ${film.release_date}",
                    style = MaterialTheme.typography.bodySmall
                )

                if (film.rating.isNotEmpty()) {
                    Text(
                        text = "Rating: ${film.rating}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}


@Composable
fun FilmsListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListarViewModel,
    onAddFilm: () -> Unit,
    onEditFilm: (Films) -> Unit
) {
    val estado = viewModel.state

    //Se encarga de controllar el estado, y de cuando mostrarse y cuando no
    val snackbarHostState = remember { SnackbarHostState() }

    //Esto es necesario para lanzar el snackbar, mientras tanto esta en "reposo"
    val scope = rememberCoroutineScope()

    //Tenemos un scaffold para poder meter el controlador de nuestro snackbar
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        CompositionLocalProvider(LocalCardStyle provides CardStyle) {
            when (estado) {
                is ListarState.Loading -> {}

                is ListarState.NoData -> {}

                is ListarState.Success -> {
                    FilmsListContent(
                        modifier = modifier.padding(padding),
                        filmsList = estado.dataset,
                        eventos = FilmsListEvents(
                            onEditFilm = onEditFilm,

                            //Cuando se elimine la pelicula lanza el snackbar
                            onDelete = { film ->
                                viewModel.delete(film)
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Película eliminada"
                                    )
                                }
                            },
                            onAddFilm = onAddFilm
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun FilmsListContent(
    modifier: Modifier = Modifier,
    filmsList: List<Films>,
    eventos: FilmsListEvents
) {
    val listState = rememberLazyListState()
    var peliculaBorrar by remember { mutableStateOf<Films?>(null) }
    LazyColumn(
        state = listState,
        modifier = modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        items(filmsList) { film ->
            ListarPelicula(
                modifier = Modifier.combinedClickable(
                    onClick = {
                        eventos.onEditFilm(film)
                    },
                    onLongClick = { peliculaBorrar = film }
                ),
                film = film
            )
        }
    }
    if (peliculaBorrar != null) {
        AlertDialogOkCancel(
            title = stringResource(R.string.borar_pelicula),
            text = stringResource(R.string.titulo_pelicula, peliculaBorrar!!.title),
            okText = stringResource(R.string.ok),
            cancelText = stringResource(R.string.cancel),
            onConfirm = {
                eventos.onDelete(peliculaBorrar!!)
                peliculaBorrar = null
            },
            onDismiss = { peliculaBorrar = null },
        )
    }
}

