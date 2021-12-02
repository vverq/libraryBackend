package ru.pinguin.librarybackend.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.pinguin.librarybackend.config.properties.ExternalApiProperties

@SpringBootApplication(scanBasePackages = ["ru.pinguin"])
@EnableConfigurationProperties(ExternalApiProperties::class)
class LibraryBackendApplication

fun main(args: Array<String>) {
    runApplication<LibraryBackendApplication>(*args)
}
