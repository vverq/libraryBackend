package ru.pinguin.library.analytics.config

import org.apache.ibatis.annotations.Mapper
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration

@Configuration
@MapperScan(basePackages = ["ru.pinguin"], annotationClass = Mapper::class)
class MyBatisConfig {
}