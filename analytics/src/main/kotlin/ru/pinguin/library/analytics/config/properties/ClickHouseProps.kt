package ru.pinguin.library.analytics.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "clickhouse")
data class ClickHouseProps(val servers: String, val user: String, val password: String, val dbname: String)