package example.com.ichnaea.core.exceptions

class TypeNotFoundException(id: Int) : EntityNotFoundException("Type", id)