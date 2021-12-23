package ru.pinguin.librarybackend.books

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import ru.pinguin.librarybackend.data.book.Book
import ru.pinguin.librarybackend.dto.BookInfo
import ru.pinguin.librarybackend.dto.CreateBookRequest
import ru.pinguin.librarybackend.dto.UpdateBookRequest

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BooksDataMapper {

    fun map(book: Book): BookInfo

    fun map(request: CreateBookRequest): Book

    @Mappings(
        value = [
            Mapping(target = "isbn", source = "isbn"),
            Mapping(target = "title", source = "request.title"),
            Mapping(target = "authors", source = "request.authors"),
            Mapping(target = "description", source = "request.description"),
            Mapping(target = "year", source = "request.year")
        ]
    )
    fun map(isbn: String, request: UpdateBookRequest): Book
}