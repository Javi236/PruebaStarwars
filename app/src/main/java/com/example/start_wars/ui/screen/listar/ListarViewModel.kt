package com.example.start_wars.ui.screen.listar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.start_wars.data.Films
import com.example.start_wars.data.model.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ListarViewModel @Inject constructor(
    private val repository: FilmsRepository
) : ViewModel() {

    var state by mutableStateOf<ListarState>(ListarState.NoData)
        private set

    init {
        getItemList()
    }

    fun getItemList() {
        state = ListarState.Loading

        viewModelScope.launch {
            repository.films
                .collect { items ->
                    state = if (items.isEmpty()) {
                        ListarState.NoData
                    } else {
                        ListarState.Success(items)
                    }
                }
        }
    }

    fun delete(item: Films) {
        repository.delete(item)
        // getItemList()
    }

}
