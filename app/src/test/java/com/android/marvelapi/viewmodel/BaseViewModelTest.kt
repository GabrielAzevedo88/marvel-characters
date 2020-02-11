package com.android.marvelapi.viewmodel

import android.view.View
import com.android.marvelapi.internal.State
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BaseViewModelTest {

    lateinit var sut: BaseViewModel

    @Before
    fun setup() {
        sut = BaseViewModel()
    }

    @Test
    fun `quando desejo mostrar o objeto então deve retornar visivel`() {
        sut.run {
            Assert.assertEquals(View.VISIBLE, getVisibility())
        }
    }

    @Test
    fun `quando desejo coultar o objeto então deve retornar gone`() {
        sut.run {
            Assert.assertEquals(View.GONE, getVisibility(false))
        }
    }

    @Test
    fun `quando o app esta carregando então deve ter apenas o loader visivel`() {
        sut.run {
            setState(State.LOADING)

            Assert.assertEquals(View.VISIBLE, mainLoaderVisibility.value)
            Assert.assertEquals(View.GONE, errorVisibility.value)
            Assert.assertEquals(View.GONE, successVisibility.value)
            Assert.assertEquals(View.GONE, toolbarVisibility.value)
        }
    }

    @Test
    fun `quando ocorre algum erro na request então deve aparecer a mensagem de erro`() {
        sut.run {
            setState(State.ERROR)

            Assert.assertEquals(View.VISIBLE, errorVisibility.value)
            Assert.assertEquals(View.GONE, mainLoaderVisibility.value)
            Assert.assertEquals(View.GONE, successVisibility.value)
            Assert.assertEquals(View.VISIBLE, toolbarVisibility.value)
        }
    }

    @Test
    fun `quando carrega as informações então o conteudo deve aparecer`() {
        sut.run {
            setState(State.SUCCESS)

            Assert.assertEquals(View.VISIBLE, successVisibility.value)
            Assert.assertEquals(View.GONE, errorVisibility.value)
            Assert.assertEquals(View.GONE, mainLoaderVisibility.value)
            Assert.assertEquals(View.VISIBLE, toolbarVisibility.value)
        }
    }
}
