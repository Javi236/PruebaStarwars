package com.example.start_wars.ui.screen.listar

import com.example.start_wars.data.Films

sealed class ListarState {
    data object Loading : ListarState()
    data object NoData : ListarState()
    data class Success(val dataset: List<Films>) : ListarState()
}