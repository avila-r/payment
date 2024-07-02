package com.avila.transaction.error

import org.springframework.http.HttpStatus

/**
 * Enum representing authorization-related errors that can occur in the system
 */
enum class AuthorizationError ( override val status: HttpStatus, override val message: String ) : APIError {

    /**
     * Error indicating that authorization service is unavailable.
     */
    UNSTABLE_AUTHORIZATION_SERVICE (
        HttpStatus.SERVICE_UNAVAILABLE,
        "Authorization service is unavailable"
    ),

    /**
     * Error indicating that an invalid authorization response was received.
     */
    INVALID_AUTHORIZATION_RESPONSE (
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Invalid authorization response"
    );

}
