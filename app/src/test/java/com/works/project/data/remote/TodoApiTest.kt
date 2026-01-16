package com.works.project.data.remote

import com.google.gson.Gson
import com.works.project.domain.model.TodoData
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Tag("unit")
class TodoApiTest {

    private lateinit var server: MockWebServer
    private lateinit var api: TodoApi

    @BeforeEach
    fun setUp() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }

    @AfterEach
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getTodos_success() = runBlocking {
        val todoData = null
        val json = Gson().toJson(todoData)
        val response = MockResponse()
            .setBody(json)
            .setResponseCode(200)

        server.enqueue(response)

        val result = api.getTodos().execute().body()

        assertEquals(todoData, result)
    }
}