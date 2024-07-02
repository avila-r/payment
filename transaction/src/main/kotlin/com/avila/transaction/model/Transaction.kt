package com.avila.transaction.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table

import org.springframework.data.annotation.CreatedDate

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Table(name = "transactions")
@Entity class Transaction (

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    @Id var id: UUID? = null,

    @JoinColumn(name = "payer_id", nullable = false)
    val payer: Long,

    @JoinColumn(name = "payee_id", nullable = false)
    val payee: Long,

    val value: BigDecimal,

    @CreatedDate
    @Column(nullable = false)
    var createdAt: LocalDateTime? = null

)

data class TransactionRequest (
    val payer: Long,
    val payee: Long,
    val value: BigDecimal
)

fun TransactionRequest.build() = Transaction (
    payer = this.payer,
    payee = this.payee,
    value = this.value
)
