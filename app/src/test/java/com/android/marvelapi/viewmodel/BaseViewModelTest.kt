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
    fun `when you want to show the object then return visible`() {
        sut.run {
            Assert.assertEquals(View.VISIBLE, getVisibility())
        }
    }

    @Test
    fun `when I want to match the object then it must return gone`() {
        sut.run {
            Assert.assertEquals(View.GONE, getVisibility(false))
        }
    }

    @Test
    fun `when the app is loading then it should only have the loader visible`() {
        sut.run {
            setState(State.LOADING)

            Assert.assertEquals(View.VISIBLE, mainLoaderVisibility.value)
            Assert.assertEquals(View.GONE, errorVisibility.value)
            Assert.assertEquals(View.GONE, successVisibility.value)
            Assert.assertEquals(View.GONE, toolbarVisibility.value)
        }
    }

    @Test
    fun `when an error occurs in the request then the error message should appear`() {
        sut.run {
            setState(State.ERROR)

            Assert.assertEquals(View.VISIBLE, errorVisibility.value)
            Assert.assertEquals(View.GONE, mainLoaderVisibility.value)
            Assert.assertEquals(View.GONE, successVisibility.value)
            Assert.assertEquals(View.VISIBLE, toolbarVisibility.value)
        }
    }

    @Test
    fun `when you load the information then the content should appear`() {
        sut.run {
            setState(State.SUCCESS)

            Assert.assertEquals(View.VISIBLE, successVisibility.value)
            Assert.assertEquals(View.GONE, errorVisibility.value)
            Assert.assertEquals(View.GONE, mainLoaderVisibility.value)
            Assert.assertEquals(View.VISIBLE, toolbarVisibility.value)
        }
    }
}
