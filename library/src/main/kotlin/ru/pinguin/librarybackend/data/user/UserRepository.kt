package ru.pinguin.librarybackend.data.user

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class UserRepository(@PersistenceContext private val em: EntityManager) {

    fun persist(user: User) = em.persist(user)

    fun existsByUsername(username: String): Boolean =
        em.createNativeQuery("select exists (select 1 from {h-schema}users where username = :username)")
            .setParameter("username", username)
            .singleResult as Boolean
}