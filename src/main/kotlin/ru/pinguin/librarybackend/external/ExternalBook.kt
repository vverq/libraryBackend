package ru.pinguin.librarybackend.external

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ExternalBook(
    var authors: List<Map<String, String>>?,
    var title: String,
    var description: Map<String, String>?,
    @JsonProperty("first_sentence") var firstSentence: Map<String, String>?,
    @JsonProperty("publish_date") var publishDate: String
)