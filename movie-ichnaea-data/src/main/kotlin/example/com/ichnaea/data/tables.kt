package example.com.ichnaea.data

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object ShowTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
    val description = text("description")
    val releaseYear = integer("year")
    val runtime = integer("runtime")

    val showType = varchar("showType", 7)
}

object GenreTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
}

object ShowGenreTable : Table() {
    val show = reference("show_id", ShowTable.id, ReferenceOption.CASCADE).primaryKey()
    val genre = reference("genre_id", GenreTable.id, ReferenceOption.CASCADE).primaryKey()
}