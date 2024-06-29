package com.avila.transaction.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
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

    @ManyToOne @JoinColumn(name = "payer_id")
    val payer: Customer,

    @ManyToOne @JoinColumn(name = "payee_id")
    val payee: Customer,

    val value: BigDecimal,

    @CreatedDate
    var createdAt: LocalDateTime? = null

)
