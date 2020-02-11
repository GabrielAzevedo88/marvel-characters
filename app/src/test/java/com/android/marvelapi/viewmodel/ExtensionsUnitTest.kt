package com.android.marvelapi.viewmodel

import com.android.marvelapi.extensions.fullImagePath
import com.android.marvelapi.extensions.md5
import com.android.marvelapi.model.Thumbnail
import org.junit.Assert
import org.junit.Test

class ExtensionsUnitTest {

    @Test
    fun `quando desejo converter para o MD5 então deve retornar um HASH valido`() {
        val key = "29011988"
        val expect = "1dddc8fa422a90d086bd4b3ea325ac52"

        Assert.assertEquals(expect, key.md5())
    }

    @Test
    fun `busca o path completo de um Thumbnail`() {
        val thumbnailMock = Thumbnail("png", "xablau")
        val expect = "xablau.png"

        Assert.assertEquals(expect, thumbnailMock.fullImagePath())
    }
}
