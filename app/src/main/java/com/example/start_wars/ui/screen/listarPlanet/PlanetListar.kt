package com.example.start_wars.ui.screen.listarPlanet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.start_wars.R
import com.example.start_wars.composables.CardStyle
import com.example.start_wars.composables.LocalCardStyle
import com.example.start_wars.data.model.Planet
import com.example.start_wars.ui.screen.alertDialog.AlertDialogOkCancel
import kotlinx.coroutines.launch

// Eventos para interactuar con la lista
data class PlanetListEvents(
    val onDelete: (Planet) -> Unit,
    val onEditPlanet: (Planet) -> Unit,
    val onAddPlanet: () -> Unit,
)

@Composable
fun PlanetListScreen(
    modifier: Modifier = Modifier,
    viewModel: PlanetListViewModel,
    onAddPlanet: () -> Unit,
    onEditPlanet: (Planet) -> Unit
) {
    val estado = viewModel.state
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddPlanet) {
                Icon(Icons.Default.Add, contentDescription = "Añadir Planeta")
            }
        }
    ) { padding ->
        CompositionLocalProvider(LocalCardStyle provides CardStyle) {
            Box(modifier = modifier.padding(padding)) {
                when (estado) {
                    is PlanetListState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is PlanetListState.NoData -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No se encontraron planetas en la galaxia.")
                        }
                    }
                    is PlanetListState.Success -> {
                        PlanetListContent(
                            planetList = estado.dataset,
                            eventos = PlanetListEvents(
                                onEditPlanet = onEditPlanet,
                                onDelete = { planet ->
                                    viewModel.onDelete(planet)
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Planeta ${planet.name} eliminado")
                                    }
                                },
                                onAddPlanet = onAddPlanet
                            )
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanetListContent(
    modifier: Modifier = Modifier,
    planetList: List<Planet>,
    eventos: PlanetListEvents
) {
    val listState = rememberLazyListState()
    var planetBorrar by remember { mutableStateOf<Planet?>(null) }

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        items(planetList, key = { it.id }) { planet ->
            ListarItemPlanet(
                modifier = Modifier.combinedClickable(
                    onClick = { eventos.onEditPlanet(planet) },
                    onLongClick = { planetBorrar = planet }
                ),
                planet = planet
            )
        }
    }

    if (planetBorrar != null) {
        AlertDialogOkCancel(
            title = "Eliminar de los archivos Jedi",
            text = "¿Estás seguro de que quieres borrar el planeta ${planetBorrar!!.name}?",
            okText = "Eliminar",
            cancelText = "Cancelar",
            onConfirm = {
                eventos.onDelete(planetBorrar!!)
                planetBorrar = null
            },
            onDismiss = { planetBorrar = null },
        )
    }
}

@Composable
fun ListarItemPlanet(modifier: Modifier = Modifier, planet: Planet) {
    val style = LocalCardStyle.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = style.backgroundColor,
            contentColor = style.contentColor
        ),
        border = BorderStroke(style.borderWidth, style.borderColor),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono: Si es desierto usamos Terrain, si no Public
            val icon = if (planet.terrain.contains("desert", ignoreCase = true)) {
                Icons.Default.Terrain
            } else {
                Icons.Default.Public
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = style.borderColor
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = planet.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Terreno: ${planet.terrain}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Población: ${planet.population}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Clima: ${planet.climate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = style.contentColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanetListPreview() {
    val mockPlanets = listOf(
        Planet(1, "Coruscant", "temperate", "cityscape", "1000000000000", "1", "12240"),
        Planet(2, "Hoth", "frozen", "tundra, ice caves", "unknown", "1.1", "7200")
    )
    CompositionLocalProvider(LocalCardStyle provides CardStyle) {
        PlanetListContent(
            planetList = mockPlanets,
            eventos = PlanetListEvents({}, {}, {})
        )
    }
}