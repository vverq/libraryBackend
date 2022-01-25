package ru.pinguin.library.analytics.books

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.pinguin.library.analytics.dto.BookShortInfo
import ru.pinguin.library.analytics.rate.RateRepository
import ru.pinguin.librarybackend.exception.AlreadyExistsException

@Service
class AnalyticsService(
    private val repository: RateRepository,
    private val objectMapper: ObjectMapper,
    @Value("\${library.base-path}") private val libraryBasePath: String
) {

    fun getAllBooks(): List<BookShortInfo> = repository
        .getAllBooksWithAvg()
        .map {
            BookShortInfo(
                it.isbn,
                it.title,
                it.rate
            )
        }

    fun rateBook(username: String, isbn: String, rate: Int) {
        sendRate(username, isbn, rate)
        repository.rateBook(username, isbn, rate)
    }

    private fun sendRate(username: String, isbn: String, rate: Int) {
        val httpClient = OkHttpClient()
        val request = Request.Builder().post(RequestBody.create(MediaType.get("application/json"), ByteArray(0))).url("${libraryBasePath}/api/v1/library/book/${isbn}/rate?username=${username}&rate=${rate}").build()
        val execute = httpClient.newCall(request).execute()
        execute.body()?.close()
        if (execute.code() == 409)
            throw AlreadyExistsException("Вы уже оценили эту книгу")
        if (execute.code() != 200)
            throw Exception("Необрабатываемая ошибка")
    }

    private fun parseArray(json: String): List<String> =
        objectMapper.readValue(json, objectMapper.typeFactory.constructCollectionType(List::class.java, String::class.java))
}