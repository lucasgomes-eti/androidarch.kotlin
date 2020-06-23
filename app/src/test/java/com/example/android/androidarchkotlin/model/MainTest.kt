package com.example.android.androidarchkotlin.model

import com.google.gson.Gson
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainTest {

    private lateinit var main: Main

    @Before
    fun createMain() {
        val mainJson = javaClass.classLoader!!
            .getResource("api-response/api-response.json").readText(Charsets.UTF_8)
        main = Gson().fromJson(mainJson, Main::class.java)
    }

    @Test
    fun toEntityTest() {
        assertThat(
            main.toEntity().nazarLocation,
            `is`(
                "Today madam nazar is at ambarino, " +
                    "precisely in cumberland forest, " +
                    "and close to: window rock, fort wallace, whinyard strait."
            )
        )
    }
}