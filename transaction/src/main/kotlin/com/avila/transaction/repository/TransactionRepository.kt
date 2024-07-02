package com.avila.transaction.repository

import com.avila.transaction.model.Transaction

import org.springframework.data.repository.ListCrudRepository

import java.util.UUID

/**
 * Repository interface for managing transactions in the database.
 *
 * Extends ListCrudRepository for CRUD operations on Transaction entities.
 */
interface TransactionRepository : ListCrudRepository<Transaction, UUID> {

    /**
     * Finds a transaction by its UUID.
     *
     * @param uuid The nullable UUID of the transaction to find.
     * @return The transaction with the specified UUID, or null if not found.
     */
    fun findById(uuid: UUID?): Transaction?

    /**
     * Finds a product by its name.
     *
     * @param id The payer's id.
     * @return The transaction's history associated to specified payer, or an empty list if not found transactions.
     */
    fun findAllByPayer(id: Long?): List<Transaction?>

    /**
     * Finds a product by its name.
     *
     * @param id The payee's id.
     * @return The transaction's history associated to specified payee, or an empty list if not found transactions.
     */
    fun findAllByPayee(id: Long?): List<Transaction?>

}
