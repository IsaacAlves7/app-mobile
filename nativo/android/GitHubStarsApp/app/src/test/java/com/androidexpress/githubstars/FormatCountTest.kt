package com.androidexpress.githubstars

import com.androidexpress.githubstars.ui.components.formatCount
import org.junit.Assert.assertEquals
import org.junit.Test

class FormatCountTest {

    @Test
    fun `numeros pequenos aparecem sem sufixo`() {
        assertEquals("42", formatCount(42))
    }

    @Test
    fun `milhares recebem sufixo k`() {
        assertEquals("1.2k", formatCount(1200))
    }

    @Test
    fun `milhoes recebem sufixo M`() {
        assertEquals("2.5M", formatCount(2_500_000))
    }
}
