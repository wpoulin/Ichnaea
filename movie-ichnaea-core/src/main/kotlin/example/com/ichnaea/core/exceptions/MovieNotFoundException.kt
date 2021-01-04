package example.com.ichnaea.core.exceptions

class MovieNotFoundException(id: Int) : EntityNotFoundException("Movie", id)
