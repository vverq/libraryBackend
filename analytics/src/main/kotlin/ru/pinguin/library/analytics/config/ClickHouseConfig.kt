package ru.pinguin.library.analytics.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.pinguin.library.analytics.config.properties.ClickHouseProps
import ru.yandex.clickhouse.BalancedClickhouseDataSource
import ru.yandex.clickhouse.settings.ClickHouseProperties
import java.util.concurrent.TimeUnit

@Configuration
class ClickHouseConfig {

    @Bean
    fun clickhouseDataSource(properties: ClickHouseProps): BalancedClickhouseDataSource =
        BalancedClickhouseDataSource(properties.servers, ClickHouseProperties().apply {
            maxPartitionsPerInsertBlock = 0
            socketTimeout = 60000
            dataTransferTimeout = 30000
            maxExecutionTime = socketTimeout + dataTransferTimeout
            if (properties.user.isNotEmpty() && properties.password.isNotEmpty()) {
                user = properties.user
                password = properties.password
            }
        }).apply {
            scheduleActualization(30, TimeUnit.SECONDS)
        }
}