package com.luizlemetech.forum.config

import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class FlywayManualConfig(private val dataSource: DataSource) {

    @PostConstruct
    fun migrate() {
        Flyway.configure()
            .locations("classpath:db/migration")
            .dataSource(dataSource)
            .load()
            .migrate()
    }
}
