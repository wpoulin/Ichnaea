package example.com.ichnaea.data

import example.com.ichnaea.models.Movie
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class IchnaeaDal(private val db: Database) {
    fun fetchMovie(id: Int): Movie? {
        return transaction(db) {
            MovieTable
                    .select { MovieTable.id.eq(id) }
                    .firstOrNull()
                    ?.toMovie()
        }
    }
}