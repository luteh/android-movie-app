package com.luteh.movieapp.common.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class DateUtilsTest {

    @Test
    fun testChangeStrDateFormat() {
        val unformattedStrDate = "2020-08-14"
        val expectedStrDate = "2020"

        val formattedStrDate = DateUtils.changeStrDateFormat(unformattedStrDate, DateUtils.DATE_FORMAT_SHORT_US, DateUtils.DATE_FORMAT_YEAR)

        assertEquals(expectedStrDate, formattedStrDate)
    }
}