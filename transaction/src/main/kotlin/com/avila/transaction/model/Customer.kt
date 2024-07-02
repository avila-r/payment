package com.avila.transaction.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

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

data class CustomerRequest (
    val name: String,
    val cpf: String,
    val email: String,
    val password: String,
    val type: String
)

fun CustomerRequest.build() = Customer (
    name = this.name,
    cpf = this.cpf,
    email = this.email,
    password = this.password,
    type = this.type,
    balance = 0.toBigDecimal()
)
