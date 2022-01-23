package ru.pinguin.librarybackend.books

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.pinguin.librarybackend.data.book.BookRepository
import ru.pinguin.librarybackend.data.rate.BookRate
import ru.pinguin.librarybackend.data.rate.RateId
import ru.pinguin.librarybackend.data.rate.RateRepository
import ru.pinguin.librarybackend.dto.BookInfo
import ru.pinguin.librarybackend.dto.CreateBookRequest
import ru.pinguin.librarybackend.dto.RateInfo
import ru.pinguin.librarybackend.dto.UpdateBookRequest
import ru.pinguin.librarybackend.exception.AlreadyExistsException
import ru.pinguin.librarybackend.exception.NotFoundException
import ru.pinguin.librarybackend.external.ExternalApiService
import ru.pinguin.librarybackend.utils.cleanIsbn

@Service
class BooksService(
    private val bookRepository: BookRepository,
    private val rateRepository: RateRepository,
    private val externalApiService: ExternalApiService,
    private val dataMapper: BooksDataMapper
) {

    fun getBookInfo(isbn: String): BookInfo = dataMapper.map(bookRepository.getBookInfo(isbn) ?: throw NotFoundException("Книга не найдена"))

    @Transactional
    fun createBook(request: CreateBookRequest) {
        if (bookRepository.existsByIsbn(cleanIsbn(request.isbn))) {
            throw AlreadyExistsException("Книга уже зарегистрирована в системе.")
        }
        bookRepository.persist(dataMapper.map(request).apply { isbn = cleanIsbn(isbn) })
    }

    @Transactional
    fun updateBook(requestIsbn: String, request: UpdateBookRequest) {
        if (!bookRepository.existsByIsbn(cleanIsbn(requestIsbn))) {
            throw AlreadyExistsException("Книга не зарегистрирована в системе")
        }
        bookRepository.merge(dataMapper.map(requestIsbn, request).apply { isbn = cleanIsbn(requestIsbn) })
    }

    @Transactional
    fun createBookFromIsbn(isbn: String): BookInfo {
        val book = externalApiService.findBookByIsbn(isbn)
        bookRepository.persist(book)
        return dataMapper.map(book)
    }

    @Transactional
    fun rateBook(username: String, isbn: String, rate: Int) {
        if (rateRepository.alreadyRated(username, isbn)) {
            throw AlreadyExistsException("Вы уже оценили эту книгу")
        }
        rateRepository.persist(BookRate(RateId(username, isbn), rate))
    }

    fun isRated(username: String, isbn: String): RateInfo = rateRepository.getRate(username, isbn)
}