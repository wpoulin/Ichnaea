package example.com.ichnaea.data

import example.com.ichnaea.models.Genre
import example.com.ichnaea.models.Movie
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toMovie(): Movie = Movie(
        id = this[MovieTable.id],
        title = this[MovieTable.title],
        description = this[MovieTable.description],
        releaseYear = this[MovieTable.releaseYear],
        runtime = this[MovieTable.runtime]
)

fun ResultRow.toGenre(): Genre = Genre(
        id = this[GenreTable.id],
        title = this[GenreTable.title]
)