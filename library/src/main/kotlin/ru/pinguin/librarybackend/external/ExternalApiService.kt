package ru.pinguin.librarybackend.external

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import ru.pinguin.librarybackend.config.properties.ExternalApiProperties
import ru.pinguin.librarybackend.data.book.Book
import ru.pinguin.librarybackend.exception.NotFoundException
import ru.pinguin.librarybackend.utils.cleanIsbn

@Service
class ExternalApiService(
        val httpClient: OkHttpClient,
        val objectMapper: ObjectMapper,
        val properties: ExternalApiProperties
) {

    fun findBookByIsbn(isbn: String): Book {
        val request = Request.Builder().get().url("${properties.externalBaseUrl}/isbn/${cleanIsbn(isbn)}.json").build()
        val response = httpClient.newCall(request).execute()
        if (response.code() == 404)
            throw NotFoundException("Книга не найдена")
        val book = objectMapper.readValue(response.body()?.byteStream(), ExternalBook::class.java)
        return Book(
                isbn,
                book.title,
                findAuthors(book.authors?.map { it["key"]!! }),
                book.description?.get("value") ?: book.firstSentence?.get("value")
        )
    }

    fun findAuthors(urls: List<String>?): List<String>? = urls?.map { findAuthor(it) }?.toList()

    private fun findAuthor(url: String): String {
        val request = Request.Builder().get().url("${properties.externalBaseUrl}${url}.json").build()
        val response = httpClient.newCall(request).execute()
        val author = objectMapper.readValue(response.body()?.byteStream(), ExternalAuthor::class.java)
        return author.name
    }

}