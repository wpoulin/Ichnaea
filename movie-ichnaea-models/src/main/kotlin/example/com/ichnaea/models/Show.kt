package example.com.ichnaea.models

data class Show(
    val id: Int,
    val title: String,
    val description: String,
    val releaseYear: Int,
    val runtime: Int,

    val genres: List<Genre>
)