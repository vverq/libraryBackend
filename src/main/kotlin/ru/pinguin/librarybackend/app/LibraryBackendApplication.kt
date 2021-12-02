package ru.pinguin.librarybackend.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["ru.pinguin"])
class LibraryBackendApplication

fun main(args: Array<String>) {
    runApplication<LibraryBackendApplication>(*args)
}
