package example.com.ichnaea.data

import example.com.ichnaea.models.Genre
import example.com.ichnaea.models.Show
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toShow(genres: List<Genre>): Show = Show(
        id = this[ShowTable.id],
        title = this[ShowTable.title],
        description = this[ShowTable.description],
        releaseYear = this[ShowTable.releaseYear],
        runtime = this[ShowTable.runtime],
        genres = genres
)

fun ResultRow.toGenre(): Genre = Genre(
        id = this[GenreTable.id],
        title = this[GenreTable.title]
)