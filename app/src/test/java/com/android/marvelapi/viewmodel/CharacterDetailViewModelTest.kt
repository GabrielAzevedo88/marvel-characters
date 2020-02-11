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
    fun `quando o personagem não tem descrição então deve ocultar o objeto`() {
        sut.run {
            every { characterMock.description.isNotBlank() } returns false

            validateUi()
            assertEquals(View.GONE, descVisibility.value)
        }
    }

    @Test
    fun `quando não existem comics então deve aparecer a mensagem`() {
        sut.run {
            every { characterMock.comics.items.isEmpty() } returns true
            every { characterMock.comics.items.isNotEmpty() } returns false

            validateUi()
            assertEquals(View.VISIBLE, noComicsVisibility.value)
        }
    }
}
