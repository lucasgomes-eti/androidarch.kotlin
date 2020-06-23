package com.example.android.androidarchkotlin.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.create

@RunWith(JUnit4::class)
class MainServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: MainService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(create(GsonBuilder().create()))
            .build()
            .create(MainService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getMain() {
        enqueueResponse("api-response.json")
        val result = (runBlocking { service.getMain() }).body()

        val request = mockWebServer.takeRequest()
        assertThat(request.path, `is`("/location/current"))

        assertThat(result, notNullValue())
        assertThat(result!!.data.location.region.name, `is`("ambarino"))
        assertThat(result.data.location.region.precise, `is`("cumberland forest"))
        for ((index, value) in result.data.location.nearBy.iterator().withIndex()) {
            when (index) {
                0 -> assertThat(value, `is`("window rock"))
                1 -> assertThat(value, `is`("fort wallace"))
                2 -> assertThat(value, `is`("whinyard strait"))
            }
        }
    }

    private fun enqueueResponse(filename: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$filename")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8))
        )
    }
}