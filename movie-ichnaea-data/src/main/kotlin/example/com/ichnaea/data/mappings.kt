package example.com.ichnaea.data

import example.com.ichnaea.models.Movie
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toMovie(): Movie = Movie(
        id = this[MovieTable.id],
        title = this[MovieTable.title],
        year = this[MovieTable.year],
        runtime = this[MovieTable.runtime]
)