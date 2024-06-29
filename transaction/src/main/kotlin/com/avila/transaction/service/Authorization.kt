package com.avila.transaction.service

import com.avila.transaction.model.Transaction

import org.springframework.web.client.RestClient

private val client = RestClient.create()

data class Authorization ( val authorized: Boolean )

fun authorize( transaction: Transaction ): String? { // TODO: Return Result<V, E>

    // TODO: Logging

    val authorization =
        client.get()
            .uri("") // TODO: Authorization service endpoint
            .retrieve()
            .toEntity(Authorization::class.java)

    if (authorization.statusCode.isError) {
        // TODO: Error
    }

    val message = authorization.body ?: return null // TODO: Error

    if (!message.authorized) {
        // TODO: Error
    }

    return "authorized"

}
