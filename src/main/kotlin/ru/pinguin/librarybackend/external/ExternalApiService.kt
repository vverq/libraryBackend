package ru.pinguin.librarybackend.external

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import ru.pinguin.librarybackend.config.properties.ExternalApiProperties
import ru.pinguin.librarybackend.data.Book
import ru.pinguin.librarybackend.exception.NotFoundException
import kotlin.streams.toList

@Service
class ExternalApiService(
    val httpClient: OkHttpClient,
    val objectMapper: ObjectMapper,
    val properties: ExternalApiProperties
) {

    fun findBookByIsbn(isbn: String): Book {
        val request = Request.Builder().get().url("${properties.externalBaseUrl}/isbn/${isbn}.json").build()
        val response = httpClient.newCall(request).execute()
        if (response.code() == 404)
            throw NotFoundException("Книга не найдена")
        val book = objectMapper.readValue(response.body()?.string(), ExternalBook::class.java)
        return Book(
            isbn,
            book.title,
            findAuthors(book.authors?.stream()?.map { it["key"]!! }?.toList()),
            book.description?.get("value") ?: book.firstSentence?.get("value")
        )
    }

    fun findAuthors(urls: List<String>?): List<String>? = urls?.stream()?.map { findAuthor(it) }?.toList()

    private fun findAuthor(url: String): String {
        val request = Request.Builder().get().url("${properties.externalBaseUrl}${url}.json").build()
        val response = httpClient.newCall(request).execute()
        val author = objectMapper.readValue(response.body()?.string(), ExternalAuthor::class.java)
        return author.name
    }

}