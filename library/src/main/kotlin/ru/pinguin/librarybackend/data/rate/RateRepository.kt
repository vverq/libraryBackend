package ru.pinguin.librarybackend.data.rate

import org.springframework.stereotype.Repository
import ru.pinguin.librarybackend.dto.RateInfo
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

    fun getRate(username: String, isbn: String): RateInfo {
        val query = """
            select 
                new ru.pinguin.librarybackend.dto.RateInfo(true, r.rate)
            from 
                BookRate r
            where 
                r.rateId.isbn = :isbn
                and r.rateId.username = :username
        """.trimIndent()
        return em.createQuery(query, RateInfo::class.java)
            .setParameter("username", username)
            .setParameter("isbn", isbn)
            .resultStream
            .findFirst()
            .orElseGet {
                RateInfo(false, 0)
            }
    }
}