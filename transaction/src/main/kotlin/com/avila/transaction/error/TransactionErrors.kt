package com.avila.transaction.error

import org.springframework.http.HttpStatus

/**
 * Enum representing transaction-related errors that can occur in the system
 */
enum class TransactionError ( override val status: HttpStatus, override val message: String ) : APIError {

    /**
     * Error indicating that doesn't exist registered products.
     */
    NO_TRANSACTIONS_REGISTERED (
        HttpStatus.NOT_FOUND,
        "No transactions registered"
    );

    /**
     * @return Error's message
     */
    override fun toString() = message

}
