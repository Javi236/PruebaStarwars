package com.example.start_wars.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.start_wars.composables.Converters
import com.example.start_wars.data.dao.FilmsDao
import com.example.start_wars.data.model.Films
import com.example.start_wars.data.dao.PersonDao
import com.example.start_wars.data.dao.PlanetDao
import com.example.start_wars.data.model.FilmPlanetEntity
import com.example.start_wars.data.model.Person
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import com.example.start_wars.data.model.Planet

@Database(
    version = 2,
    entities = [Films::class, Person::class, Planet::class, FilmPlanetEntity::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun getFilmsDao(): FilmsDao
    abstract fun getPersonDao(): PersonDao
    abstract fun getPlanetDao(): PlanetDao

    companion object {
        /**
         * La variable se guarda en memoria. Cualquier cambio realizado en la variable por un hilo
         * se refleja de inmediado y es visible al resto de hilos. No hay copias antiguas o nulas.
         */
        @Volatile
        private var INSTANCE: StarWarsDatabase? = null

        fun getDatabase(context: Context): StarWarsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StarWarsDatabase::class.java,
                    "starwars_database.db"
                )
                    // 2. CAMBIO IMPORTANTE: Permitir migración destructiva
                    // Si la versión del dispositivo es menor que la versión del código (2),
                    // y no hay una migración manual definida, Room borrará la base de datos
                    // y la creará de nuevo.
                    .fallbackToDestructiveMigration()
                    // Callback para pre-poblar la base de datos
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Se utiliza un executor para realizar la inserción en un hilo de fondo
                            //Las tareas se ejecutan de forma secuencial en un hilo/s
                            Executors.newSingleThreadExecutor().execute {
                                INSTANCE?.let { database ->
                                    prepopulateDatabase(database)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
        fun prepopulateDatabase(database: StarWarsDatabase) {
            val filmsDao = database.getFilmsDao()
            val personDao = database.getPersonDao()
            val planetDao = database.getPlanetDao()

            runBlocking {
                // Insertamos películas de ejemplo
                filmsDao.insert(
                    Films(
                        title = "A New Hope",
                        episode_id = 4,
                        opening_crawl = "It is a period of civil war...",
                        director = "George Lucas",
                        producer = "Gary Kurtz, Rick McCallum",
                        release_date = "1977-05-25",
                        era = "Original Trilogy",
                        rating = "8.6",
                        is_original_trilogy = true,
                        species = "",
                        starships = "",
                        vehicles = "",
                        characters = "",
                        planets = "",
                        url = "https://swapi.dev/api/films/1/",
                        created = "1977-05-25",
                        edited = "1977-05-25"
                    )
                )

                filmsDao.insert(
                    Films(
                        title = "The Empire Strikes Back",
                        episode_id = 1,
                        opening_crawl = "It is a dark time for the Rebellion...",
                        director = "Irvin Kershner",
                        producer = "Gary Kurtz, Rick McCallum",
                        release_date = "1980-05-21",
                        era = "Original Trilogy",
                        rating = "8.7",
                        is_original_trilogy = true,
                        species = "",
                        starships = "",
                        vehicles = "",
                        characters = "",
                        planets = "",
                        url = "https://swapi.dev/api/films/2/",
                        created = "1980-05-21",
                        edited = "1980-05-21"
                    )
                )

                personDao.insert(
                    Person(
                        id = 1,
                        name = "Luke Skywalker",
                        height = "172",
                        mass = "77",
                        hairColor = "blond",
                        skinColor = "fair",
                        eyeColor = "blue",
                        birthYear = "19BBY",
                        gender = "male",
                        imgStarWars = 0,
                        filmId = 1
                    )
                )
                planetDao.insert(
                    Planet(
                        1,
                        name = "Tatooine",
                        climate = "arid",
                        terrain = "desert",
                        population = "200000",
                        gravity = "1 standard",
                        diameter = "10465"
                    )
                )
                planetDao.insert(
                    Planet(
                        2,
                        name = "ESPAÑA",
                        climate = "arid",
                        terrain = "desert",
                        population = "200000",
                        gravity = "1 standard",
                        diameter = "10465"
                    )
                )

                filmsDao.insertJoinFilmPlanet(FilmPlanetEntity(filmId = 1, planetId = 1))

                filmsDao.insertJoinFilmPlanet(FilmPlanetEntity(filmId = 1, planetId = 2))

                val resultFilm = filmsDao.getFilmWithPlanet(episodeId = 1)
                println(" - Pelicula: ${resultFilm.films.title}")
                println(" - Planetas: ${resultFilm.planet}")

                val resultPersonWithFilm = personDao.getPersonWithFilms(1)
                print("${resultPersonWithFilm.person.name} nacio en ${resultPersonWithFilm.film.title}")
            }
        }
    }
}