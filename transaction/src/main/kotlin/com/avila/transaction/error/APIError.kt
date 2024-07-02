package com.avila.transaction.error

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
 * Extension function to retrieve the Spring's Response Entity from any implementation of [APIError].
 * @return The response entity.
 */
fun APIError.response() = APIErrorResponse (
    status = this.status,
    message = this.message
)

/**
 * Data class representing an API error response.
 */
data class APIErrorResponse (
    val status: HttpStatus,
    val message: String
)

/**
 * Extension function to convert an [APIErrorResponse] to an [APIError] implementation.
 * @return An object implementing [APIError] with the status and message from the [APIErrorResponse].
 */
fun APIErrorResponse.build(): APIError {
    val status = this.status
    val message = this.message

    return object : APIError {
        override val status: HttpStatus = status
        override val message: String = message
    }
}
