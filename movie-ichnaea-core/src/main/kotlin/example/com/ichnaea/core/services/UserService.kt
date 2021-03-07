package example.com.ichnaea.core.services

import example.com.ichnaea.core.exceptions.UserNotFoundException
import example.com.ichnaea.data.IchnaeaDal
import example.com.ichnaea.models.Show
import example.com.ichnaea.models.User

class UserService(private val dal:IchnaeaDal) {

    fun fetchUser(id: Int): User {
        return dal.fetchUser(id) ?: throw UserNotFoundException(id)
    }


    fun fetchShowOfUser(id: Int): List<Show> {
        return dal.fetchShowOfUser(id)
    }
}