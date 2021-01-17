package example.com.ichnaea.core.services

import example.com.ichnaea.core.exceptions.ShowNotFoundException
import example.com.ichnaea.data.IchnaeaDal
import example.com.ichnaea.models.Show

class ShowService(private val dal: IchnaeaDal) {
    fun fetch(id: Int): Show {
        return dal.fetchShow(id) ?: throw ShowNotFoundException(id)
    }

    fun fetchAddInfos(id: Int): Show {
        return dal.fetchShowAddInfos(id) ?: throw ShowNotFoundException(id)
    }

    fun fetchAll() : List<Show>{
        return dal.fetchShows()
    }
}