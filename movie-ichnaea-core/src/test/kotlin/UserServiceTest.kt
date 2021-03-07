package example.com.ichnaea.core.services

import example.com.ichnaea.data.IchnaeaDal
import example.com.ichnaea.models.Show
import example.com.ichnaea.models.Type
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserServiceTest {

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
        val userService = UserService(dal = dal)

        // ACT
        val result = userService.fetchShowOfUser(1)

        // ASSERT
        verify { userService.fetchShowOfUser(1) }
        Assertions.assertEquals(shows, result)
    }
}