package ru.pinguin.library.analytics.books

import org.springframework.web.bind.annotation.RestController
import ru.pinguin.library.analytics.dto.BookShortInfo

@RestController
class AnalyticsController(private val analyticsService: AnalyticsService) : AnalyticsApi {
    override fun getAllBooks(): List<BookShortInfo> = analyticsService.getAllBooks()

    override fun rateBook(username: String, isbn: String, rate: Int) = analyticsService.rateBook(username, isbn, rate)
}