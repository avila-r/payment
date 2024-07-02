package com.avila.transaction.error

import org.springframework.http.HttpStatus

/**
 * Enum representing transaction-related errors that can occur in the system
 */
enum class CustomerError (override val status: HttpStatus, override val message: String ) : APIError {

    /**
     * Error indicating that email is already in use.
     */
    EMAIL_ALREADY_IN_USE (
        HttpStatus.CONFLICT,
        "Email already in use"
    ),

    /**
     * Error indicating that cpf is already in use.
     */
    CPF_ALREADY_IN_USE (
        HttpStatus.CONFLICT,
        "CPF already in use"
    ),

    /**
     * Error indicating that customer already exists.
     */
    CUSTOMER_ALREADY_EXISTS (
        HttpStatus.CONFLICT,
        "Customer already exists"
    ),

    /**
     * Error indicating that doesn't exist registered customers.
     */
    NO_CUSTOMERS_REGISTERED (
        HttpStatus.NOT_FOUND,
        "No customers registered"
    ),

    /**
     * Error indicating that customer is not found.
     */
    CUSTOMER_NOT_FOUND (
        HttpStatus.NOT_FOUND,
        "Customer not found"
    );

    /**
     * @return Error's message
     */
    override fun toString() = message

}
