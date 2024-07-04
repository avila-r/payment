package com.avila.transaction.repository

import com.avila.transaction.model.Customer
import org.springframework.data.repository.ListCrudRepository

/**
 * Repository interface for managing customers in the database.
 *
 * Extends ListCrudRepository for CRUD operations on Customer entities.
 */
interface CustomerRepository : ListCrudRepository<Customer, Long> {

    /**
     * Finds a customer by its id.
     *
     * @param id The id of the customer to find.
     * @return The customer with the specified UUID, or null if not found.
     */
    fun findCustomerById(id: Long?): Customer?

    /**
     * Finds a customer by its name.
     *
     * @param name The name of the customer to find.
     * @return The customer with the specified name, or null if not found.
     */
    fun findByName(name: String): Customer?

    /**
     * Finds a customer by its CPF (Cadastro de Pessoas Físicas - Brazilian individual taxpayer registry identification).
     *
     * @param cpf The CPF of the customer to find.
     * @return The customer with the specified CPF, or null if not found.
     */
    fun findByCpf(cpf: String): Customer?

    /**
     * Finds a customer by its email.
     *
     * @param email The email of the customer to find.
     * @return The customer with the specified email, or null if not found.
     */
    fun findByEmail(email: String): Customer?

    /**
     * Finds all customers by their type (Common or Seller).
     *
     * @param type The type of the customers to find.
     * @return A list of customers with the specified type, or an empty list if none found.
     */
    fun findAllByType(type: String): List<Customer?>

}
