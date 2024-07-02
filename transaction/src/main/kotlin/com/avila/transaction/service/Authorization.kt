package com.avila.transaction.service

import com.github.michaelbull.result.Err as err
import com.github.michaelbull.result.Ok as ok
import com.github.michaelbull.result.Result as r

import com.avila.transaction.error.APIError as Error
import com.avila.transaction.error.APIErrorResponse
import com.avila.transaction.error.AuthorizationError
import com.avila.transaction.error.build
import com.avila.transaction.model.Transaction

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.client.RestClient

private val client = RestClient.create()

@Value("\${services.authorization.uri}")
private lateinit var uri: String

@Value("\${services.authorization.endpoints.authorize-transaction}")
private lateinit var endpoint: String

private data class Authorization (
    val authorized: Boolean,
    val error: APIErrorResponse?
)

fun authorize(transaction: Transaction): r<Boolean, Error>  {

    val authorization =
        client.post()
            .uri(uri + endpoint)
            .body(transaction)
            .retrieve()
            .toEntity(Authorization::class.java)

    val body: Authorization = authorization.body ?: return err(AuthorizationError.INVALID_AUTHORIZATION_RESPONSE)

    if (authorization.statusCode.isError) {
        return err(body.error?.build() ?: AuthorizationError.UNSTABLE_AUTHORIZATION_SERVICE)
    }

    return ok(true)

}
