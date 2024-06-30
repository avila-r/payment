package com.avila.authorization.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Transaction (
    val id: UUID?,
    val payer: Long,
    val payee: Long,
    val value: BigDecimal,
    val createdAt: LocalDateTime?
)
