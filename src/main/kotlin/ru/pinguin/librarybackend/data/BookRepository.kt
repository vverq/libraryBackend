package ru.pinguin.librarybackend.data

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class BookRepository (@PersistenceContext val em: EntityManager) {

    fun persist(book: Book) {
        em.persist(book)
    }

}