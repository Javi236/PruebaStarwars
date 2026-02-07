package com.example.start_wars.ui.home

import Action
import BaseTopAppBarState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.start_wars.data.model.Films
import com.example.start_wars.ui.screen.about.AboutUsScreen
import com.example.start_wars.ui.screen.detail.DetailScreen
import com.example.start_wars.ui.screen.listar.FilmsListScreen
import javax.inject.Singleton


fun NavGraphBuilder.filmGraph(
    navController: NavHostController,
    modifier: Modifier,
    iconMenu: Painter,
    iconBack: Painter,
    onConfigureTopBar: (BaseTopAppBarState) -> Unit,
    onOpenDrawer: () -> Unit
) {
    // Pantalla de Listado
    composable(Routes.LISTAR) {
        LaunchedEffect(Unit) {
            onConfigureTopBar(
                BaseTopAppBarState(
                    title = "Star Wars Films",
                    iconUpAction = iconMenu,
                    upAction = { onOpenDrawer() },
                    actions = listOf(
                        Action.ActionImageVector(
                            name = "Añadir",
                            icon = Icons.Default.Add,
                            contentDescription = "Añadir película",
                            onClick = { navController.navigate(Routes.CREACION) }
                        ),
                        Action.ActionImageVector(
                            name = "Sobre nosotros",
                            icon = Icons.Default.MoreVert,
                            contentDescription = "Información",
                            isVisible = true,
                            onClick = { navController.navigate(Routes.ABOUT) }
                        )
                    )
                )
            )
        }

    }

    // Pantalla de Creación (Alta)
    composable(Routes.CREACION) {
        LaunchedEffect(Unit) {
            onConfigureTopBar(
                BaseTopAppBarState(
                    title = "Nueva Película",
                    iconUpAction = iconBack,
                    upAction = { navController.popBackStack() },
                    actions = emptyList()
                )
            )
        }

        DetailScreen(
            modifier = modifier,
            film = null,
            viewModel = hiltViewModel(),
            goToBack = { navController.popBackStack() }
        )
    }

    // Pantalla de Edición
    composable(Routes.EDITAR) {
        val pelicula = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<Films>("clave")

        LaunchedEffect(pelicula) {
            onConfigureTopBar(
                BaseTopAppBarState(
                    title = "Editar Película",
                    iconUpAction = iconBack,
                    upAction = { navController.popBackStack() },
                    actions = listOf(
                        Action.ActionImageVector(
                            name = "Actualizar",
                            icon = Icons.Default.Check,
                            contentDescription = "Actualizar",
                            onClick = { /* Lógica de guardado si fuera necesaria desde aquí */ }
                        )
                    )
                )
            )
        }

        DetailScreen(
            modifier = modifier,
            film = pelicula,
            viewModel = hiltViewModel(),
            goToBack = { navController.popBackStack() }
        )
    }

    // Pantalla Sobre Nosotros
    composable(Routes.ABOUT) {
        LaunchedEffect(Unit) {
            onConfigureTopBar(
                BaseTopAppBarState(
                    title = "Sobre Nosotros",
                    iconUpAction = iconBack,
                    upAction = { navController.popBackStack() },
                    actions = emptyList()
                )
            )
        }
        AboutUsScreen()
    }
}