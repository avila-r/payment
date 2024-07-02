package com.avila.authorization.repository

import com.avila.authorization.model.Customer

import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Long> {
    fun findById(id: Long?): Customer?
}
