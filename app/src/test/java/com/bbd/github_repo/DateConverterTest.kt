package com.bbd.github_repo

import com.bbd.github_repo.util.toSimpleDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


class DateConverterTest {

    private val expectedDates = arrayOf("2016-02-02", "2022-01-15", "2009-03-24", "2022-02-04")
    private val actualDates = arrayOf(
        "2016-02-02T16:41:10Z",
        "2022-01-15T21:25:34Z",
        "2009-03-24T16:09:53Z",
        "2022-02-04T04:54:45Z"
    )

    @ParameterizedTest()
    @ValueSource(ints = [0, 1, 2, 3])
    fun rightDateFormat_shouldReturnSuccess(index: Int) {

        println(actualDates[index].toRegex())
        assertEquals(expectedDates[index], actualDates[index].toSimpleDate())
    }

    @Test
    fun wrongDateFormat_shouldReturnUnSupportedDate() {
        assertEquals("UnSupportedDate", "2016/02/02".toSimpleDate())
    }

    @Test
    fun wrongMonthDayOrder_shouldNotSupport() {
        assertNotEquals("2022-01-15", "2022-15-01T21:25:34Z".toSimpleDate())
    }

    @Test
    fun wrongYearMonthOrder_shouldNotSupport() {
        assertEquals("UnSupportedDate", "15-2022-01T21:25:34Z".toSimpleDate())
    }

    @Test
    fun reverseOrder_shouldNotSupport() {
        assertEquals("UnSupportedDate", "15-01-2022T21:25:34Z".toSimpleDate())
    }

}