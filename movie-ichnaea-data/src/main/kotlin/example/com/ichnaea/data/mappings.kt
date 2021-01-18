package example.com.ichnaea.data

import example.com.ichnaea.models.Genre
import example.com.ichnaea.models.Show
import example.com.ichnaea.models.Type
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toShow(): Show = Show(
        id = this[ShowTable.id],
        title = this[ShowTable.title],
        description = this[ShowTable.description],
        releaseYear = this[ShowTable.releaseYear],
        runtime = this[ShowTable.runtime],

        typeId = this[ShowTable.typeId],
)

fun ResultRow.toGenre(): Genre = Genre(
        id = this[GenreTable.id],
        title = this[GenreTable.title]
)

fun ResultRow.toType(): Type = Type(
        id = this[TypeTable.id],
        type = this[TypeTable.type]
)