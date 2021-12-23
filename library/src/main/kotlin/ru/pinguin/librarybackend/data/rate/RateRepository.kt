package ru.pinguin.librarybackend.data.rate

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class RateRepository(@PersistenceContext private val em: EntityManager) {

    fun persist(rate: BookRate) = em.persist(rate)

    fun alreadyRated(username: String, isbn: String): Boolean =
        em.createNativeQuery("select exists (select 1 from {h-schema}user_rates where username=:username and isbn=:isbn)")
            .setParameter("username", username)
            .setParameter("isbn", isbn)
            .singleResult as Boolean

}