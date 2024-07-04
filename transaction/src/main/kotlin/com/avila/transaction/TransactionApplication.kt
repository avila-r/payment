package com.avila.transaction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class TransactionApplication

fun main(args: Array<String>) {
	runApplication<TransactionApplication>(*args)
}
