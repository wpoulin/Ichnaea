package example.com.ichnaea.data

import example.com.ichnaea.models.Genre
import example.com.ichnaea.models.Show
import example.com.ichnaea.models.Type
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class IchnaeaDal(private val db: Database) {
    // Basic fetch
    fun fetchShow(id: Int): Show? {
        return transaction(db) {
            ShowTable
                    .select {ShowTable.id.eq(id) }
                    .firstOrNull()
                    ?.toShow()
        }
    }

    fun fetchShows(): List<Show> {
        return transaction(db) {
            GenreTable
                    .innerJoin(ShowGenreTable)
                    .innerJoin(ShowTable)
                    .selectAll()
                    .mapNotNull { it.toShow() }
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

    fun fetchType(id: Int): Type? {
        return transaction(db) {
            TypeTable
                    .select { TypeTable.id.eq(id) }
                    .firstOrNull()
                    ?.toType()
        }
    }




    // Fetch Additional info for a show
    fun fetchGenreShow(id: Int): List<Genre> {
        return transaction(db) {
            GenreTable
                    .innerJoin(ShowGenreTable)
                    .innerJoin(ShowTable)
                    .slice(GenreTable.columns)
                    .select { ShowTable.id.eq(id) }
                    .mapNotNull { it.toGenre() }
        }
    }



    // Create entities
    fun createShow(title: String, description: String, releaseYear: Int, runtime: Int, showType: Type): Show? {
        val id = transaction(db) {
            ShowTable.insert {
                it[this.title] = title
                it[this.description] = description
                it[this.releaseYear] = releaseYear
                it[this.runtime] = runtime
                it[this.typeId] = showType.id
            } get ShowTable.id
        }
        return fetchShow(id)
    }

    fun createGenre(title: String): Genre? {
        val id = transaction(db) {
            GenreTable.insert {
                it[this.title] = title
            } get GenreTable.id
        }
        return fetchGenre(id)
    }

    fun createType(type: String): Type? {
        val id = transaction(db) {
            TypeTable.insert {
                it[this.type] = type
            } get TypeTable.id
        }
        return fetchType(id)
    }



    // Add Additional info to a show
    fun addGenre(show: Show, genre: Genre) {
        transaction(db) {
            ShowGenreTable.insert {
                it[this.show] = show.id
                it[this.genre] = genre.id
            }
        }
    }

}