package ru.pinguin.librarybackend.config

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.pinguin.librarybackend.config.properties.ExternalApiProperties

@Configuration
class ExternalApiConfig {

    @Bean
    fun httpClient() : OkHttpClient = OkHttpClient()

}