package com.avila.authorization.error

import org.springframework.http.HttpStatus

/**
 * Enum representing transaction-related errors that can occur in the system
 */
enum class TransactionError ( override val status: HttpStatus, override val message: String ) : APIError {

    /**
     * Error indicating that a transaction is invalid.
     */
    INVALID_TRANSACTION (
        HttpStatus.UNAUTHORIZED,
        "Invalid transaction"
    ),

    /**
     * Error indicating that a transaction's payer was not found.
     */
    PAYER_NOT_FOUND (
        HttpStatus.NOT_FOUND,
        "Transaction's payer not found"
    ),

    /**
     * Error indicating that a transaction's payee was not found.
     */
    PAYEE_NOT_FOUND (
        HttpStatus.NOT_FOUND,
        "Transaction's payee not found"
    );

    /**
     * @return Error with custom message
     */
    fun message(message: String) = object : APIError {
        override val status = this@TransactionError.status
        override val message = message
    }

    /**
     * @return Error's message
     */
    override fun toString() = message

}
