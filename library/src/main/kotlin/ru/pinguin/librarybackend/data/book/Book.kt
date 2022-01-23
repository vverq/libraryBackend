package ru.pinguin.librarybackend.data.book

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Table(name = "books")
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
data class Book(
    @Id @Column(name = "isbn") var isbn: String,
    @Column(name = "title") var title: String,
    @Column(name = "authors") var authors: String?,
    @Column(name = "description") var description: String?,
    @Column(name = "year") var year: Int? = 1970
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Book

        return isbn == other.isbn
    }

    override fun hashCode(): Int = 1756406093
}
