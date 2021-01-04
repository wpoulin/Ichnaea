package example.com.ichnaea.data

import org.jetbrains.exposed.sql.Table

object MovieTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
    val year = integer("year")
    val runtime = integer("runtime")
}