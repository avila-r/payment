package com.avila.transaction.service

import com.avila.transaction.error.AuthorizationError
import com.avila.transaction.error.TransactionError
import com.github.michaelbull.result.Err as err
import com.github.michaelbull.result.Ok as ok
import com.github.michaelbull.result.Result as r

import com.avila.transaction.error.APIError as Error
import com.avila.transaction.model.Transaction
import com.avila.transaction.model.credit
import com.avila.transaction.model.debit
import com.avila.transaction.repository.CustomerRepository
import com.avila.transaction.repository.TransactionRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service class for managing transactions.
 *
 * @param repository The repository for product data access.
 */
@Service class TransactionService ( private val repository: TransactionRepository, private val customerRepository: CustomerRepository ) {

    /**
     * Creates a new transaction.
     *
     * @param transaction The request containing transaction data.
     * @return An Ok result containing the created product if successful, or an Err result with an error if validation fails or saving fails.
     */
    @Transactional fun create(transaction: Transaction): r<Transaction, Error> {

        // Validate transaction
        val authorization = authorize(transaction)

        if (authorization.isErr) {
            return err(authorization.error)
        }

        // Notify transaction
        // {TODO}

        // Transfer
        val err = transfer(transaction)

        if (err != null) {
            return err(err)
        }

        return ok(repository.save(transaction))

    }

    private fun transfer(transaction: Transaction): Error? {

        val sender = customerRepository.findCustomerById(transaction.payer) ?: return AuthorizationError.INVALID_AUTHORIZATION_RESPONSE

        customerRepository.save(sender.debit(transaction.value))

        val receiver = customerRepository.findCustomerById(transaction.payee) ?: return AuthorizationError.INVALID_AUTHORIZATION_RESPONSE

        customerRepository.save(receiver.credit(transaction.value))

        return null

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
