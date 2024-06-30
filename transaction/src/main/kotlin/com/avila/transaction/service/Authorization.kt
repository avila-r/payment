package com.avila.transaction.service

import com.avila.transaction.model.Transaction

import org.springframework.beans.factory.annotation.Value

import org.springframework.web.client.RestClient

private val client = RestClient.create()

@Value("\${services.authorization.uri}")
private lateinit var uri: String

@Value("\${services.authorization.endpoints.authorize-transaction}")
private lateinit var endpoint: String

data class Authorization ( val authorized: Boolean )

fun authorize(transaction: Transaction): String? { // TODO: Return Result<V, E>

    // TODO: Logging

    val authorization =
        client.post()
            .uri(uri + endpoint)
            .body(transaction)
            .retrieve()
            .toEntity(Authorization::class.java)

    if (authorization.statusCode.isError) {
        // TODO: Error: unstable authorization service. Cancel transaction.
    }

    val message = authorization.body ?: return null // TODO: Error

    if (!message.authorized) {
        // TODO: Error: unauthorized transaction
    }

    return "authorized"

}
