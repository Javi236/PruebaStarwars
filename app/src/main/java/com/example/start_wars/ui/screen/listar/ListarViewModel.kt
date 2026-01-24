package com.example.start_wars.ui.screen.listar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.start_wars.data.model.Films
import com.example.start_wars.data.repository.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListarViewModel @Inject constructor(
    private val repository: FilmsRepository
) : ViewModel() {

    var state by mutableStateOf<ListarState>(ListarState.Loading)
        private set

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            repository.getData().collect { films ->
                state = if (films.isEmpty()) {
                    ListarState.NoData
                } else {
                    ListarState.Success(films)
                }
            }
        }
    }

    fun onDelete(film: Films) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFilm(film)
        }
    }
}
