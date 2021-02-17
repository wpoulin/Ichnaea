package example.com.ichnaea.core.services

import com.google.gson.Gson
import example.com.ichnaea.core.exceptions.GenreNotFoundException
import example.com.ichnaea.core.exceptions.ShowNotFoundException
import example.com.ichnaea.core.exceptions.TypeNotFoundException
import example.com.ichnaea.data.IchnaeaDal
import example.com.ichnaea.models.*
import org.joda.time.DateTime
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ShowService(private val dal: IchnaeaDal) {
    private val apiKey = ""

    fun fetch(id: Int): Show {
        return dal.fetchShow(id) ?: throw ShowNotFoundException(id)
    }

    fun fetchGenreOfShow(id: Int): List<Genre> {
        return dal.fetchGenreOfShow(id)
    }

    fun fetchShowUser(id: Int): List<Show> {
        return dal.fetchShowOfUser(id)
    }

    fun fetchAll() : List<Show>{
        return dal.fetchShows()
    }

    fun fetchType(id: Int): Type {
        return dal.fetchType(id) ?: throw TypeNotFoundException(id)
    }

    // Todo make an exception type with name instead of id
    fun fetchTypeByName(name: String): Type {
        return dal.fetchTypeByName(name) ?: throw TypeNotFoundException(0)
    }

    fun fetchGenre(id: Int): Genre {
        return dal.fetchGenre(id) ?: throw GenreNotFoundException(id)
    }

    // Todo make an exception type with name instead of id
    fun fetchGenreByName(name: String): Genre? {
        return dal.fetchGenreByName(name)
    }

    fun addGenreToShow(show: Show, genre: Genre) {
        dal.addGenre(show, genre)
    }

    fun createShow(title: String, description: String, releaseYear: Int, runtime: Int, showType: Type): Show? {
        return dal.createShow(title, description, releaseYear, runtime, showType)
    }

    fun createGenre(title: String): Genre? {
        return dal.createGenre(title)
    }

    fun createShowRating(userId: Int, showId: Int, rating: Double) {
        val todayDate = DateTime()
        dal.addShowRating(userId, showId, rating, todayDate)
    }

    fun formatShowTitleForOMDb(showTitle: String) : String {
        return showTitle.replace(" ","+")
    }

    fun formatRuntimeFromOMDb(runtime: String) : Int {
        return runtime.removeSuffix(" min").toInt()
    }

    fun extractGenreFromOMDb(genreConcatenated: String): List<String> {
        return genreConcatenated.replace(" ", "").split(",").toList()
    }

    fun fetchShowInformationOMDb(showTitle: String) :OMDbRequest {
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.omdbapi.com/?t=$showTitle&apikey=$apiKey"))
                .build();
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return Gson().fromJson(response.body(), OMDbRequest::class.java)
    }

    fun addUserRating(userRating: UserRating) {
        val titleFormatted = formatShowTitleForOMDb(userRating.title)
        val showOMDb = fetchShowInformationOMDb(titleFormatted)
        val runtimeFormatted = formatRuntimeFromOMDb(showOMDb.Runtime)
        val type = fetchTypeByName(showOMDb.Type)
        val createdShow = createShow(showOMDb.Title, showOMDb.Plot, showOMDb.Year.toInt(), runtimeFormatted, type)

        if (createdShow != null) {
            for (genre in extractGenreFromOMDb(showOMDb.Genre)) {
                val genreObject = fetchGenreByName(genre)
                if (genreObject != null) {
                    addGenreToShow(createdShow,genreObject)
                }
                else {
                    val createdGenre = createGenre(genre)
                    if (createdGenre != null) {
                        addGenreToShow(createdShow, createdGenre)
                    }
                }
            }
            createShowRating(userRating.userId, createdShow.id, userRating.rating)
        }
    }

}