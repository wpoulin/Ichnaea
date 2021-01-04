package example.com.ichnaea.core.services

import example.com.ichnaea.core.exceptions.MovieNotFoundException
import example.com.ichnaea.data.IchnaeaDal
import example.com.ichnaea.models.Movie

class MovieService(private val dal: IchnaeaDal) {
    fun fetch(id: Int): Movie {
        return dal.fetchMovie(id) ?: throw MovieNotFoundException(id)
    }
}