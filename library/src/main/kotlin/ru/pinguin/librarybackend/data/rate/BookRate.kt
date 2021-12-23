package ru.pinguin.librarybackend.data.rate

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table


@Table(name = "user_rates")
@Entity
data class BookRate(@EmbeddedId var rateId: RateId, @Column(name = "rate") var rate: Int)
