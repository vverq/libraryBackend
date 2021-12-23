package ru.pinguin.library.analytics.config

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.zalando.problem.ProblemModule

@Configuration
class JacksonConfig {

    @Bean
    fun problemObjectMapperModules(): Jackson2ObjectMapperBuilderCustomizer? =
        Jackson2ObjectMapperBuilderCustomizer { jacksonObjectMapperBuilder: Jackson2ObjectMapperBuilder ->
            jacksonObjectMapperBuilder.modules(
                listOf(
                    JavaTimeModule(),
                    KotlinModule.Builder().build(),
                    ProblemModule()
                )
            )
        }

}