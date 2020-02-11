package com.android.marvelapi.viewmodel

import com.android.marvelapi.repository.MarvelDataRepository
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CharacterListViewModelTest {

    lateinit var sut: CharacterListViewModel
    private val repository: MarvelDataRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        sut = CharacterListViewModel(repository)
    }

    @Test
    fun `quando passo um valor de página então deve retornar um valor válido do offset`() {

        sut.run {
            val page = 5
            val expect = 100

            assertEquals(expect, getOffsetValue(page))
        }
    }
}
