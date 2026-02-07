package com.example.start_wars.ui.screen.listarPlanet

import com.example.start_wars.data.model.Planet


sealed class PlanetListState {
    data object Loading: PlanetListState()
    data object NoData: PlanetListState()
    data class Success(val dataset: List<Planet>): PlanetListState()
}