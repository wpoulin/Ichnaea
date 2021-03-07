package example.com.ichnaea.core.exceptions

abstract class EntityWithNameNotFoundException(entity: String, name: String) : Exception("$entity with '$name' was not found")