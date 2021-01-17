package example.com.ichnaea.data

import example.com.ichnaea.models.Genre
import example.com.ichnaea.models.Show
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class IchnaeaDal(private val db: Database) {
    fun fetchShow(id: Int): Show? {
        return transaction(db) {
            ShowTable.select {ShowTable.id.eq(id) }
                    .firstOrNull()
                    ?.toShow(emptyList())
        }
    }

    fun fetchShowAddInfos(id: Int): Show? {
        return transaction(db) {
            val genre = GenreTable.innerJoin(ShowGenreTable)
                    .innerJoin(ShowTable)
                    .slice(GenreTable.columns)
                    .select { ShowTable.id.eq(id) }
                    .mapNotNull { it.toGenre() }
            ShowTable
                    .innerJoin(ShowGenreTable)
                    .innerJoin(GenreTable)
                    .select { ShowTable.id.eq(id) }
                    .firstOrNull()
                    ?.toShow(genre)
        }
    }

    fun fetchShows(): List<Show> {
        return transaction(db) {
            GenreTable.innerJoin(ShowGenreTable)
                    .innerJoin(ShowTable)
                    .selectAll()
                    .groupBy({ it.toShow(emptyList()) }) { it.toGenre() }
                    .entries.map { (show, genres) ->
                        show.copy(genres = genres)
                    }
        }
    }

    fun fetchGenre(id: Int): Genre? {
        return transaction(db) {
            GenreTable
                    .select { GenreTable.id.eq(id) }
                    .firstOrNull()
                    ?.toGenre()
        }
    }

    fun createGenre(title: String): Genre? {
        val id = transaction(db) {
            GenreTable.insert {
                it[this.title] = title
            } get GenreTable.id
        }
        return fetchGenre(id)
    }

    fun createShow(title: String, description: String, releaseYear: Int, runtime: Int): Show? {
        val id = transaction(db) {
            ShowTable.insert {
                it[this.title] = title
                it[this.description] = description
                it[this.releaseYear] = releaseYear
                it[this.runtime] = runtime
            } get ShowTable.id
        }
        return fetchShow(id)
    }

    fun addGenreToShow(show: Show, genre: Genre) {
        transaction(db) {
            ShowGenreTable.insert {
                it[this.show] = show.id
                it[this.genre] = genre.id
            }
        }
    }
}