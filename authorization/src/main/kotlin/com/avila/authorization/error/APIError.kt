package com.avila.authorization.error

import org.springframework.http.HttpStatus

/**
 * Interface defining the structure for API errors.
 *
 * @author R. Ávila
 */
interface APIError {
    val status: HttpStatus
    val message: String
}

/**
 * Data class representing an API error response.
 */
data class APIErrorResponse (
    val status: HttpStatus,
    val message: String
)

/**
 * Extension function to retrieve the Spring's Response Entity from any implementation of [APIError].
 * @return The response entity.
 */
fun APIError.response() = APIErrorResponse (
    status = this.status,
    message = this.message
)
