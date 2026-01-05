package com.example.start_wars.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.start_wars.data.Films
import com.example.start_wars.ui.screen.about.AboutUsScreen
import com.example.start_wars.ui.screen.detail.DetailScreen
import com.example.start_wars.ui.screen.listar.FilmsListScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

object Routes {
    const val LISTAR = "list"
    const val EDITAR = "edit"
    const val CREACION = "alta"
    const val ABOUT = "about"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostScreen(
    navController: NavHostController = rememberAnimatedNavController(),
    modifier: Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.LISTAR,
        modifier = modifier,

        //Animacion basica con mas duracion de lo normal para que sea visible
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500)) + scaleIn(
                initialScale = 0.85f,
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500)) + scaleOut(
                targetScale = 1.10f,
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500)) + scaleIn(
                initialScale = 1.10f,
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500)) + scaleOut(
                targetScale = 0.85f,
                animationSpec = tween(500)
            )
        }

    ) {

        composable(Routes.LISTAR) {
            FilmsListScreen(
                modifier = modifier,
                viewModel = hiltViewModel(),
                onAddFilm = { navController.navigate(Routes.CREACION) },
                onEditFilm = { pelicula ->
                    // Guardamos la peli para edit
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("clave", pelicula)

                    navController.navigate(Routes.EDITAR)
                }
            )
        }

        composable(Routes.CREACION) {
            DetailScreen(
                modifier = modifier,
                film = null,
                viewModel = hiltViewModel(),
                goToBack = { navController.popBackStack() }
            )
        }

        composable(Routes.EDITAR) {
            val pelicula = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Films>("clave")

            DetailScreen(
                modifier = modifier,
                film = pelicula,
                viewModel = hiltViewModel(),
                goToBack = { navController.popBackStack() }
            )
        }

        composable(Routes.ABOUT) {
            AboutUsScreen()
        }
    }
}
