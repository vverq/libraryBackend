package ru.pinguin.library.analytics.books

import org.springframework.web.bind.annotation.RestController
import ru.pinguin.library.analytics.dto.BookShortInfo

@RestController
class BooksController(private val booksService: BooksService) : BooksApi {
    override fun getAllBooks(): List<BookShortInfo> = booksService.getAllBooks()

    override fun rateBook(username: String, isbn: String, rate: Int) = booksService.rateBook(username, isbn, rate)
}