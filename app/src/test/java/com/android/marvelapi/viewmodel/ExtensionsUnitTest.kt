package com.android.marvelapi.viewmodel

import com.android.marvelapi.extensions.fullImagePath
import com.android.marvelapi.extensions.md5
import com.android.marvelapi.model.Thumbnail
import org.junit.Assert
import org.junit.Test

class ExtensionsUnitTest {

    @Test
    fun `when I want to convert to MD5 then I must return a valid HASH`() {
        val key = "29011988"
        val expect = "1dddc8fa422a90d086bd4b3ea325ac52"

        Assert.assertEquals(expect, key.md5())
    }

    @Test
    fun `takes the full path of a Thumbnail`() {
        val thumbnailMock = Thumbnail("png", "xablau")
        val expect = "xablau.png"

        Assert.assertEquals(expect, thumbnailMock.fullImagePath())
    }
}
