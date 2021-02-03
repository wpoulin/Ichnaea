package example.com.ichnaea.data

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object ShowTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
    val description = text("description")
    val releaseYear = integer("year")
    val runtime = integer("runtime")

    val typeId = reference("type_id", TypeTable.id)
}

object GenreTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
}

object ShowGenreTable : Table() {
    val show = reference("show_id", ShowTable.id, ReferenceOption.CASCADE).primaryKey()
    val genre = reference("genre_id", GenreTable.id, ReferenceOption.CASCADE).primaryKey()
}

object TypeTable: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val type = text("title")
}

object UserTable: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val firstName = text("firstName")
    val lastName = text("lastName")
}

object ShowUserTable: Table() {
    val show = reference("show_id", ShowTable.id, ReferenceOption.CASCADE).primaryKey()
    val user = reference("user_id", UserTable.id, ReferenceOption.CASCADE).primaryKey()
    val rating = double("rating")
    val timestamp = datetime("timestamp")
}