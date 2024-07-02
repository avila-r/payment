package com.avila.authorization.model

import jakarta.persistence.Table
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Column

import java.math.BigDecimal

@Table(name = "customers")
@Entity class Customer (

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @Id var id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val cpf: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val type: String,

    @Column(nullable = false)
    val balance: BigDecimal

)

fun Customer.canPay(value: BigDecimal) = this.balance >= value

fun Customer.isCommon() = this.type == "COMMON"
