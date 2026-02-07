package com.example.start_wars.ui.screen.listarPlanet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.start_wars.data.model.Planet
import com.example.start_wars.data.repository.PlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(
    private val repository: PlanetRepository
): ViewModel() {
    var state by mutableStateOf<PlanetListState>(PlanetListState.Loading)

        private set

    init {
        getData()
    }

    private fun getData(){
        viewModelScope.launch {
            repository.getData().collect { planets ->
                state=if (planets.isEmpty()){
                    PlanetListState.NoData
                }else{
                    PlanetListState.Success(planets)
                }
            }
        }
    }

    fun onDelete(planet: Planet){
        viewModelScope.launch (Dispatchers.IO){
            repository.removePlanet(planet)
        }
    }
}