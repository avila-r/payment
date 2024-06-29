package com.avila.transaction.environment

import com.fasterxml.jackson.databind.ObjectMapper

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@Import(JacksonObjectMapperConfiguration::class, ComponentDependencyConfiguration::class)
@ActiveProfiles("test")
open class ApplicationTest

@ComponentScan(basePackages = ["com.avila.transaction"])
@Configuration
class ComponentDependencyConfiguration

@Configuration
class JacksonObjectMapperConfiguration {
    @Bean fun publishParser() = ObjectMapper()
}
