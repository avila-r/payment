package com.avila.transaction.handler

import com.avila.transaction.model.CustomerRequest
import com.avila.transaction.model.build
import com.avila.transaction.service.CustomerService

import jakarta.validation.Valid

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller class for handling customer-related API requests.
 * This class defines endpoints for creating, updating, deleting, and retrieving customers.
 *
 * @param service The service for customer management.
 * @constructor Creates an HTTP Handler with the specified service.
 * @author R. Ávila
 */
@RequestMapping("/customers")
@RestController class CustomerHandler ( private val service: CustomerService ) : Handler {

    /**
     * Endpoint for registering customers.
     * Validates the request body using the @Valid annotation.
     *
     * @param request The customer DTO request data.
     * @return ResponseEntity containing the result of the transfer operation.
     */
    @PostMapping
    fun register(@Valid @RequestBody request: CustomerRequest) = handle(service.create(request.build()))

    /**
     * Endpoint for listing all customers.
     *
     * @return ResponseEntity containing the list of all customers.
     */
    @GetMapping
    fun customers() = handle(service.getAll())

    /**
     * Endpoint for retrieving a customer by its ID.
     *
     * @param id The ID of the customer to be retrieved.
     * @return ResponseEntity containing the result of the customer retrieval operation.
     */
    @GetMapping("/id/{id}")
    fun customerById(@PathVariable id: Long) {} // TODO

}
