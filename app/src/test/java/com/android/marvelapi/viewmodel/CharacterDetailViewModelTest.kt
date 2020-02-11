package com.android.marvelapi.viewmodel

import android.view.View
import com.android.marvelapi.model.Character
import com.android.marvelapi.repository.MarvelDataRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CharacterDetailViewModelTest() {

    lateinit var sut: CharacterDetailViewModel
    private val repository: MarvelDataRepository = mockk(relaxed = true)
    private val characterMock: Character = mockk(relaxed = true)

    @Before
    fun setup() {
        sut = CharacterDetailViewModel(repository)
    }

    @Test
    fun `when the character has no description then he must hide the object`() {
        sut.run {
            every { characterMock.description.isNotBlank() } returns false

            validateUi()
            assertEquals(View.GONE, descVisibility.value)
        }
    }

    @Test
    fun `when there are no comics then the message should appear`() {
        sut.run {
            every { characterMock.comics.items.isEmpty() } returns true
            every { characterMock.comics.items.isNotEmpty() } returns false

            validateUi()
            assertEquals(View.VISIBLE, noComicsVisibility.value)
        }
    }
}
