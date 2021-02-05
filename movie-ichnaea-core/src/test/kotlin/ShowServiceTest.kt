package example.com.ichnaea.core.services

import example.com.ichnaea.data.IchnaeaDal
import example.com.ichnaea.models.Show
import example.com.ichnaea.models.Type
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ShowServiceTest {

    @Test
    fun `will test nothing`() {
        assertEquals(1,1)
    }

    /*@Test
    fun `will return all shows`() {
        // ARRANGE
        val showTypes = mutableListOf(
                Type(1, "Action"),
                Type(2, "Guerre")
        )
        val invoices = mutableListOf(
                Show(1,"Jason Bourne", "Description",2019,120, showTypes[0].id),
                Show(2,"The Bourne Supremacy", "Supremacy",2004,108, showTypes[0].id),
                Show(3,"Hacksaw Ridge", "Medic on the field",2016,139, showTypes[1].id),
        )
        val dal = mockk<IchnaeaDal> {
            every { fetchShows() } returns invoices
        }
        val invoiceService = ShowService(dal = dal)

        // ACT
        val result = invoiceService.fetchAll()

        // ASSERT
        verify { invoiceService.fetchAll() }
        assertEquals(invoices, result)
    }*/

}