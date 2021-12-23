package ru.pinguin.library.analytics.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.pinguin.library.analytics.config.properties.ClickHouseProps

@SpringBootApplication(scanBasePackages = ["ru.pinguin"])
@EnableConfigurationProperties(ClickHouseProps::class)
@EntityScan("ru.pinguin")
class LibraryAnalyticsApplication

fun main(args: Array<String>) {
    runApplication<LibraryAnalyticsApplication>(*args)
}
