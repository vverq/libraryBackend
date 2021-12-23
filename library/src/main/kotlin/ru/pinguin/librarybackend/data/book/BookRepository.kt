package ru.pinguin.librarybackend.data.book

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class BookRepository(@PersistenceContext val em: EntityManager) {

    fun persist(book: Book) = em.persist(book)
    fun merge(book: Book) = em.merge(book)

    fun existsByIsbn(isbn: String): Boolean = em
        .createNativeQuery("select exists (select 1 from {h-schema}books where isbn=:isbn)")
        .setParameter("isbn", isbn)
        .singleResult as Boolean

    fun getBookInfo(isbn: String): Book? =
        em.createQuery("select b from Book b where b.isbn = :isbn", Book::class.java)
            .setParameter("isbn", isbn)
            .resultStream
            .findFirst()
            .orElseGet(null)

}