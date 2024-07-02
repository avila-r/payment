package com.avila.transaction.service

import com.avila.transaction.error.TransactionError
import com.github.michaelbull.result.Err as err
import com.github.michaelbull.result.Ok as ok
import com.github.michaelbull.result.Result as r

import com.avila.transaction.error.APIError as Error
import com.avila.transaction.model.Transaction
import com.avila.transaction.repository.TransactionRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service class for managing transactions.
 *
 * @param repository The repository for product data access.
 */
@Service class TransactionService ( private val repository: TransactionRepository ) {

    /**
     * Creates a new transaction.
     *
     * @param request The request containing transaction data.
     * @return An Ok result containing the created product if successful, or an Err result with an error if validation fails or saving fails.
     */
    @Transactional fun create(request: Transaction): r<Transaction, Error> {

        val authorization = authorize(request)

        if (authorization.isErr) {
            return err(authorization.error)
        }

        return ok(repository.save(request))

    }

    /**
     * Retrieves all transactions.
     *
     * @return An Ok result containing a list of all transactions, or an Err result with a TransactionError if there's no transactions registered.
     */
    fun getAll(): r<List<Transaction>, Error> {

        val result = repository.findAll()

        if (result.isEmpty()) {
            return err(TransactionError.NO_TRANSACTIONS_REGISTERED)
        }

        return ok(result)

    }

}
