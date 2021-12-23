package ru.pinguin.librarybackend.config

import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExternalApiConfig {

    @Bean
    fun httpClient(): OkHttpClient = OkHttpClient()

}