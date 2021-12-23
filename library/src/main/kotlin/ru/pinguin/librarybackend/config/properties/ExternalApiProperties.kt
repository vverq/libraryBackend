package ru.pinguin.librarybackend.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "external")
data class ExternalApiProperties(val externalBaseUrl: String)