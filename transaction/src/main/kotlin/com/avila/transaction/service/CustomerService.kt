package com.avila.transaction.service

import com.github.michaelbull.result.Err as err
import com.github.michaelbull.result.Ok as ok
import com.github.michaelbull.result.Result as R

import com.avila.transaction.error.APIError as Err
import com.avila.transaction.error.CustomerError
import com.avila.transaction.model.Customer
import com.avila.transaction.repository.CustomerRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service class for managing customers.
 *
 * @param repository The repository for customer data access.
 * @author R. Ávila
 */
@Service class CustomerService ( private val repository: CustomerRepository ) {

    /**
     * Creates a new customer.
     *
     * @param request The request containing customer data.
     * @return An Ok result containing the created customer if successful, or an Err result with an error if validation fails or saving fails.
     */
    @Transactional fun create(request: Customer): R<Customer, Err> {

        val validation = repository.validate(request)

        if (validation.isErr) {
            return err(validation.error)
        }

        return ok(repository.save(request))

    }

    /**
     * Retrieves all customers.
     *
     * @return An Ok result containing a list of all customers, or an Err result with a CustomerError if there's no customers registered.
     */
    fun getAll(): R<List<Customer>, Err> {

        val result = repository.findAll()

        if (result.isEmpty()) {
            return err(CustomerError.NO_CUSTOMERS_REGISTERED)
        }

        return ok(result)

    }


    /**
     * Validates a customer registration request.
     *
     * @param request The customer request to validate.
     * @return An Ok result indicating whether the request is valid (true) or not (false), or an Err result with an error if validation fails.
     */
    private fun CustomerRepository.validate(request: Customer): R<Boolean, Err> {

        if (request.id != null && this.findCustomerById(request.id) != null)
            return err(CustomerError.CUSTOMER_ALREADY_EXISTS)

        if (this.findByEmail(request.email) != null)
            return err(CustomerError.EMAIL_ALREADY_IN_USE)

        if (this.findByCpf(request.cpf) != null)
            return err(CustomerError.CPF_ALREADY_IN_USE)

        return ok(true)

    }

}
