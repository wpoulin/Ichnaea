package example.com.ichnaea.core.exceptions

class UserNotFoundException(id: Int)  : EntityNotFoundException("User", id)