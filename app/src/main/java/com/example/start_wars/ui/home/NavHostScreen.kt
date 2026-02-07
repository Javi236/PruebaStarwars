package com.example.start_wars.ui.home

import Action
import BaseTopAppBarState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.start_wars.data.model.Films
import com.example.start_wars.ui.screen.about.AboutUsScreen
import com.example.start_wars.ui.screen.detail.DetailScreen
import com.example.start_wars.ui.screen.listar.FilmsListScreen
import javax.inject.Singleton

// --- TUS RUTAS MANTENIDAS ---
object Routes {
    const val LISTAR = "list"
    const val EDITAR = "edit"
    const val CREACION = "alta"
    const val ABOUT = "about"
}

@Composable
fun NavHostScreen(
    navController: NavHostController,
    modifier: Modifier,
    onConfigureTopBar: (BaseTopAppBarState) -> Unit,
    onOpenDrawer: () -> Unit
) {
    val iconMenu = rememberVectorPainter(Icons.Default.Menu)
    val iconBack = rememberVectorPainter(Icons.AutoMirrored.Filled.ArrowBack)

    NavHost(
        navController = navController,
        startDestination = Routes.LISTAR,
        modifier = modifier
    ) {
        // Invocamos el grafo externo
        filmGraph(
            navController = navController,
            modifier = modifier,
            iconMenu = iconMenu,
            iconBack = iconBack,
            onConfigureTopBar = onConfigureTopBar,
            onOpenDrawer = onOpenDrawer
        )
    }
}