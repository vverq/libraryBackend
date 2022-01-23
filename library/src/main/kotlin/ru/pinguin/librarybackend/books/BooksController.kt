package ru.pinguin.librarybackend.books

import org.springframework.web.bind.annotation.RestController
import ru.pinguin.librarybackend.dto.BookInfo
import ru.pinguin.librarybackend.dto.CreateBookRequest
import ru.pinguin.librarybackend.dto.RateInfo
import ru.pinguin.librarybackend.dto.UpdateBookRequest

@RestController
class BooksController(private val booksService: BooksService) : BooksApi {

    override fun getBookInfo(isbn: String): BookInfo = booksService.getBookInfo(isbn)

    override fun createBook(request: CreateBookRequest) = booksService.createBook(request)

    override fun updateBook(isbn: String, request: UpdateBookRequest) = booksService.updateBook(isbn, request)

    override fun createBookFromIsbn(isbn: String): BookInfo = booksService.createBookFromIsbn(isbn)

    override fun rateBook(username: String, isbn: String, rate: Int) = booksService.rateBook(username, isbn, rate)

    override fun isRated(username: String, isbn: String): RateInfo = booksService.isRated(username, isbn)
}