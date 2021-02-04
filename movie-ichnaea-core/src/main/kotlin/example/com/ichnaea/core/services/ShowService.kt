package example.com.ichnaea.core.services

import example.com.ichnaea.core.exceptions.ShowNotFoundException
import example.com.ichnaea.data.IchnaeaDal
import example.com.ichnaea.models.Genre
import example.com.ichnaea.models.Show
import example.com.ichnaea.models.ShowRating

class ShowService(private val dal: IchnaeaDal) {
    fun fetch(id: Int): Show {
        return dal.fetchShow(id) ?: throw ShowNotFoundException(id)
    }

    fun fetchGenreMovie(id: Int): List<Genre> {
        return dal.fetchGenreShow(id)
    }

    fun fetchAll() : List<Show>{
        return dal.fetchShows()
    }

    fun fetchShowUser(id: Int): List<Show> {
        return dal.fetchShowUser(id)
    }

    fun createShowRating(showRating: ShowRating) {
        println(showRating.title)
        println(showRating.rating)
    }
}