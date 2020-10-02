package com.luteh.core.utils

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

        val formattedStrDate = com.luteh.core.common.utils.DateUtils.changeStrDateFormat(unformattedStrDate, com.luteh.core.common.utils.DateUtils.DATE_FORMAT_SHORT_US, com.luteh.core.common.utils.DateUtils.DATE_FORMAT_YEAR)

        assertEquals(expectedStrDate, formattedStrDate)
    }
}