package com.avila.transaction.handler

import com.avila.transaction.service.TransactionService

import jakarta.validation.Valid

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller class for handling transaction-related API requests.
 * This class defines endpoints for creating, updating, deleting, and retrieving products.
 *
 * @param service The service for product management.
 * @constructor Creates an HTTP Handler with the specified service.
 * @author R. Ávila
 */
@RequestMapping("/payments")
@RestController class TransactionHandler (private val service: TransactionService) : Handler {

    @GetMapping
    fun transactions() {} // TODO

    @GetMapping("/id/{id}")
    fun transactionById(@PathVariable id: String) {} // TODO

    /**
     * Endpoint for transactions.
     * Validates the request body using the @Valid annotation.
     *
     * @param request The transaction request data.
     * @return ResponseEntity containing the result of the transfer operation.
     */
    @PostMapping("/pay")
    fun transfer(@Valid @RequestBody request: Any) {} // TODO

}
