package com.example.start_wars.ui.clases

data class Films(
    val title: String = "",
    val episode_id: Int = 0,
    val opening_crawl: String = "",
    val director: String = "",
    val producer: String = "",
    val release_date: String = "",
    val species: List<String> = emptyList(),
    val starships: List<String> = emptyList(),
    val vehicles: List<String> = emptyList(),
    val characters: List<String> = emptyList(),
    val planets: List<String> = emptyList(),
    val url: String = "",
    val created: String = "",
    val edited: String = ""
)
