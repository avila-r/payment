package com.avila.transaction.handler

import com.avila.transaction.model.TransactionRequest
import com.avila.transaction.model.build
import com.avila.transaction.service.TransactionService


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import jakarta.validation.Valid

import java.util.UUID

/**
 * Controller class for handling transaction-related API requests.
 * This class defines endpoints for creating, updating, deleting, and retrieving transactions.
 *
 * @param service The service for transaction management.
 * @constructor Creates an HTTP Handler with the specified service.
 * @author R. Ávila
 */
@RequestMapping("/payments")
@RestController class TransactionHandler (private val service: TransactionService) : Handler {

    /**
     * Endpoint for listing all transactions.
     *
     * @return ResponseEntity containing the list of all transactions.
     */
    @GetMapping
    fun transactions() = handle(service.getAll())

    /**
     * Endpoint for retrieving a transaction by its ID.
     *
     * @param uuid The ID of the transaction to be retrieved.
     * @return ResponseEntity containing the result of the transaction retrieval operation.
     */
    @GetMapping("/id/{uuid}")
    fun transactionById(@PathVariable uuid: String?) {} // TODO

    /**
     * Endpoint for transactions.
     * Validates the request body using the @Valid annotation.
     *
     * @param request The transaction request data.
     * @return ResponseEntity containing the result of the transfer operation.
     */
    @PostMapping("/pay")
    fun transfer(@Valid @RequestBody request: TransactionRequest) = handle(service.create(request.build()))

    /**
     * Converts a nullable String to a UUID or returns null if the conversion fails.
     *
     * @return The UUID parsed from the String, or null if the String is null or cannot be parsed as a UUID.
     */
    private fun String?.toUUIDOrNull(): UUID? {
        return try {
            this?.let {
                UUID.fromString(it)
            }
        } catch (e: IllegalArgumentException) {
            null
        }
    }

}
