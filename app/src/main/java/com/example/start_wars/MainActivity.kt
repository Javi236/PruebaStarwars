package com.example.start_wars

import BaseTopAppBar
import BaseTopAppBarState
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.start_wars.ui.home.NavHostScreen
import com.example.start_wars.ui.home.Routes
import com.example.start_wars.ui.screen.listarPlanet.PlanetListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            val defaultIcon = rememberVectorPainter(Icons.Default.Menu)

            var topBarState by remember {
                mutableStateOf(
                    BaseTopAppBarState(
                        title = "Star Wars",
                        iconUpAction = defaultIcon,
                        upAction = {
                            // Acción por defecto: abrir el menú
                            scope.launch { drawerState.open() }
                        },
                        actions = emptyList()
                    )
                )
            }

            MaterialTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Text(
                                text = "Menú Star Wars",
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.titleLarge
                            )
                            HorizontalDivider()

                            NavigationDrawerItem(
                                label = { Text("Listado Películas") },
                                selected = currentRoute == Routes.LISTAR,
                                onClick = {
                                    navController.navigate(Routes.LISTAR) {
                                        popUpTo(Routes.LISTAR) { inclusive = true }
                                    }
                                    scope.launch { drawerState.close() }
                                }
                            )

                            NavigationDrawerItem(
                                label = { Text("Acerca de") },
                                selected = currentRoute == Routes.ABOUT,
                                onClick = {
                                    navController.navigate(Routes.ABOUT)
                                    scope.launch { drawerState.close() }
                                }
                            )
                            /*
                            NavigationDrawerItem(
                                label = { Text("Planet") },
                                selected = currentRoute == Routes.ABOUT,
                                onClick = {
                                    navController.navigate(PlanetListScreen() { })
                                    scope.launch { drawerState.close() }
                                }
                            )*/
                        }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            BaseTopAppBar(state = topBarState)
                        },
                        floatingActionButton = {
                            if (currentRoute == Routes.LISTAR) {
                                FloatingActionButton(
                                    onClick = { navController.navigate(Routes.CREACION) }
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Añadir")
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHostScreen(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding),
                            onConfigureTopBar = { newState ->
                                topBarState = newState
                            },
                            onOpenDrawer = { scope.launch { drawerState.open() } }
                        )
                    }
                }
            }
        }
    }
}