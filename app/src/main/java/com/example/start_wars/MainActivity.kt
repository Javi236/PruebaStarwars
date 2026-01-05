package com.example.start_wars

import com.example.start_wars.ui.home.NavHostScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.start_wars.ui.home.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val entradaAnterior by navController.currentBackStackEntryAsState()
            val rutaActual = entradaAnterior?.destination?.route

            MaterialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    topBar = {
                        TopAppBar(
                            title = { Text("Star Wars") },
                            //Boton de añadir en el topbar
                            actions = {
                                IconButton(onClick = {
                                    navController.navigate(Routes.CREACION)
                                }) {
                                    Icon(
                                        Icons.Default.AddCircleOutline,
                                        contentDescription = "Acción crear"
                                    )
                                }
                                //Boton de listar en topbar
                                IconButton(onClick = {
                                    navController.navigate(Routes.LISTAR)
                                }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.List,
                                        contentDescription = "Listar"
                                    )
                                }
                                //Y el menu overflow
                                OverflowMenu(
                                    onAboutUs = {
                                        navController.navigate(Routes.ABOUT)
                                    }
                                )
                            }
                        )
                    },
                    //No he entendido muy bien el enunciado pero creo que es esto lo que se pide
                    //He puesto dos botones diferentes, uno cuando estas en listar y otro cuando ya vas a añadir una pelicula
                    floatingActionButton = {
                        if (rutaActual == Routes.LISTAR) {
                            FloatingActionButton(
                                onClick = {
                                    navController.navigate(Routes.CREACION)
                                }
                            ) {
                                Icon(
                                    Icons.Default.AddCircleOutline,
                                    contentDescription = "Crear película"
                                )
                            }
                        }
                    }

                ) { innerPadding ->
                    NavHostScreen(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun OverflowMenu(
    onAboutUs: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(
            onClick = { expanded = true }
        ) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "Más opciones"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Sobre nosotros") },
                onClick = {
                    expanded = false
                    onAboutUs()
                }
            )
        }
    }
}
