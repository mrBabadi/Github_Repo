package ir.bbd.githubrepo

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResponseFileReaderTest {

    lateinit var gson: Gson

    @BeforeEach
    fun initGson() {
        gson = GsonBuilder().create()
    }

    @Test
    fun `test mock file reader, should return success`() {
        val reader = ResponseFileReader("test.json")
        assertEquals(
            gson.fromJson(reader.content, TestModel::class.java),
            TestModel("success")
        )
    }

    @Test
    fun `test mock file reader, should not be equals`() {
        val reader = ResponseFileReader("test.json")
        assertNotEquals(
            gson.fromJson(reader.content, TestModel::class.java),
            TestModel("failed")
        )
    }
}