package example.com.ichnaea.core.exceptions

class GenreNotFoundException(id: Int) : EntityNotFoundException("Genre", id)