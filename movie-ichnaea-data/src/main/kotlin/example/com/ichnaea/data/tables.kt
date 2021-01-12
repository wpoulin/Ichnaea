package example.com.ichnaea.data

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object MovieTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
    val description = text("description")
    val releaseYear = integer("year")
    val runtime = integer("runtime")
}

object GenreTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
}

object MovieGenreTable : Table() {
    val movie = reference("movie_id", MovieTable.id, ReferenceOption.CASCADE).primaryKey()
    val genre = reference("genre_id", GenreTable.id, ReferenceOption.CASCADE).primaryKey()
}