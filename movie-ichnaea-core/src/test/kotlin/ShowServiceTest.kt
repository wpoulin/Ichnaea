package example.com.ichnaea.core.services

import example.com.ichnaea.core.exceptions.ShowNotFoundException
import example.com.ichnaea.data.IchnaeaDal
import example.com.ichnaea.models.Genre
import example.com.ichnaea.models.Show
import example.com.ichnaea.models.Type
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ShowServiceTest {

    @Test
    fun `will throw if show is not found`() {
        val dal = mockk<IchnaeaDal> {
            every { fetchShow(404) } returns null
        }
        val showService = ShowService(dal = dal)

        assertThrows<ShowNotFoundException> {
            showService.fetchShow(404)
        }
    }

    @Test
    fun `will return all shows`() {
        // ARRANGE
        val showTypes = mutableListOf(
                Type(1, "Action"),
                Type(2, "Guerre")
        )
        val shows = mutableListOf(
                Show(1,"Jason Bourne", "Description",2019,120, showTypes[0].id),
                Show(2,"The Bourne Supremacy", "Supremacy",2004,108, showTypes[0].id),
                Show(3,"Hacksaw Ridge", "Medic on the field",2016,139, showTypes[1].id),
        )
        val dal = mockk<IchnaeaDal> {
            every { fetchShows() } returns shows
        }
        val showService = ShowService(dal = dal)

        // ACT
        val result = showService.fetchAll()

        // ASSERT
        verify { showService.fetchAll() }
        assertEquals(shows, result)
    }

    @Test
    fun `will return a show corresponding to an id`() {
        // ARRANGE
        val showTypes = Type(1, "Action")
        val show = Show(2,"The Bourne Supremacy", "Supremacy",2004,108, showTypes.id)
        val dal = mockk<IchnaeaDal> {
            every { fetchShow(show.id) } returns show
        }
        val showService = ShowService(dal = dal)

        // ACT
        val result = showService.fetchShow(show.id)

        // ASSERT
        verify { showService.fetchShow(show.id) }
        assertEquals(show, result)
    }

    @Test
    fun `will return the created show`() {
        // ARRANGE
        val showTypes = Type(1, "Action")
        val show = Show(1,"Jason Bourne", "Description",2019,120, showTypes.id)

        val dal = mockk<IchnaeaDal> {
            every { createShow(show.title, show.description, show.releaseYear, show.runtime, showTypes) } returns show
        }
        val showService = ShowService(dal = dal)

        // ACT
        val result = showService.createShow(show.title, show.description, show.releaseYear, show.runtime, showTypes)

        // ASSERT
        verify { showService.createShow(show.title, show.description, show.releaseYear, show.runtime, showTypes) }
        assertEquals(show, result)
    }

    @Test
    fun `will return genre of a show corresponding to an id`() {
        // ARRANGE
        val genres = mutableListOf(
                Genre(1, "Action"),
                Genre(2, "Guerre")
        )
        val dal = mockk<IchnaeaDal> {
            every { fetchGenreOfShow(1) } returns genres
        }
        val showService = ShowService(dal = dal)

        // ACT
        val result = showService.fetchGenreOfShow(1)

        // ASSERT
        verify { showService.fetchGenreOfShow(1) }
        assertEquals(genres, result)
    }

    @Test
    fun `will return show of a user corresponding to an id`() {
        // ARRANGE
        val showTypes = Type(1, "Action")
        val shows = mutableListOf(
                Show(1,"Jason Bourne", "Description",2019,120, showTypes.id),
                Show(2,"The Bourne Supremacy", "Supremacy",2004,108, showTypes.id),
                Show(3,"Hacksaw Ridge", "Medic on the field",2016,139, showTypes.id),
        )
        val dal = mockk<IchnaeaDal> {
            every { fetchShowOfUser(1) } returns shows
        }
        val showService = ShowService(dal = dal)

        // ACT
        val result = showService.fetchShowOfUser(1)

        // ASSERT
        verify { showService.fetchShowOfUser(1) }
        assertEquals(shows, result)
    }

    // Todo implement test in case it throws an exception
    @Test
    fun `will return type corresponding to an id`() {
        // ARRANGE
        val showTypes = Type(1, "show")
        val dal = mockk<IchnaeaDal> {
            every { fetchType(1) } returns showTypes
        }
        val showService = ShowService(dal = dal)

        // ACT
        val result = showService.fetchType(1)

        // ASSERT
        verify { showService.fetchType(1) }
        assertEquals(showTypes, result)
    }

    // Todo implement test in case it throws an exception
    @Test
    fun `will return genre corresponding to an id`() {
        // ARRANGE
        val genres = Genre(1, "Action")
        val dal = mockk<IchnaeaDal> {
            every { fetchGenre(1) } returns genres
        }
        val showService = ShowService(dal = dal)

        // ACT
        val result = showService.fetchGenre(1)

        // ASSERT
        verify { showService.fetchGenre(1) }
        assertEquals(genres, result)
    }
}