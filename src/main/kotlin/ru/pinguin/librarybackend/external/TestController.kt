package ru.pinguin.librarybackend.external

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.pinguin.librarybackend.data.Book

@RestController
class TestController(private val apiService: ExternalApiService) {

    @GetMapping("test")
    fun test(@RequestParam isbn: String): Book = apiService.findBookByIsbn(isbn)

}