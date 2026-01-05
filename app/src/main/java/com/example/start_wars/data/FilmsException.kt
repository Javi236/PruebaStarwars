package com.example.start_wars.data

sealed class FilmException(msg: String) : Exception(msg) {

    data class NotFound(val msg: String = "Pelicula no encontrada") : FilmException(msg)
    data class Exists(val msg: String = "Pelicula ya existente") : FilmException(msg)
    data class None(val msg: String = "") : FilmException(msg)

}