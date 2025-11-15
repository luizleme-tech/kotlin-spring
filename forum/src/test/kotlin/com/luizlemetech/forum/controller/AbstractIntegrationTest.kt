package com.luizlemetech.forum.controller

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
abstract class AbstractIntegrationTest {

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.28")
            .apply {
                withDatabaseName("testedb")
                withUsername("testuser")
                withPassword("123456")
                withReuse(true)
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
            registry.add("spring.jpa.hibernate.ddl-auto") { "validate" }
        }
    }
}