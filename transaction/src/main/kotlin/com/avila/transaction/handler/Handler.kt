package com.avila.transaction.handler

import com.github.michaelbull.result.Result as r

import com.avila.transaction.error.APIError as Error
import com.avila.transaction.error.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * Interface defining a handler for processing Result instances and generating HTTP responses.
 */
interface Handler {

    /**
     * @param result The Result instance to be handled.
     * @return ResponseEntity containing either the error response or the success value with status 200.
     */
    fun <V, E : Error> handle(result: r<V, E>) =
        if (result.isErr)
            ResponseEntity.status(result.error.status).body(result.error.response())
        else
            ResponseEntity.ok(result.value)

    /**
     * @param status The custom HTTP status code for the response.
     * @param result The Result instance to be handled.
     * @return ResponseEntity containing either the error response or the success value with the status at param.
     */
    fun <V, E : Error> handle(status: HttpStatus, result: r<V, E>) =
        if (result.isErr)
            ResponseEntity.status(result.error.status).body(result.error.response())
        else
            ResponseEntity.status(status).body(result.value)

}
