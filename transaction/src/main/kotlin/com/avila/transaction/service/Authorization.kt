package com.avila.transaction.service

import com.github.michaelbull.result.Err as err
import com.github.michaelbull.result.Ok as ok
import com.github.michaelbull.result.Result as r

import com.avila.transaction.error.APIError as Error
import com.avila.transaction.error.APIErrorResponse
import com.avila.transaction.error.AuthorizationError
import com.avila.transaction.error.build
import com.avila.transaction.model.Transaction

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@ConfigurationProperties("services.authorization")
@Component object AuthorizationServiceProperties {
    lateinit var uri: String
    lateinit var endpoint: String
}

private data class Authorization (
    val authorized: Boolean,
    val error: APIErrorResponse?
)

fun authorize(transaction: Transaction): r<Boolean, Error>  {

    val uri = AuthorizationServiceProperties.uri
    val endpoint = AuthorizationServiceProperties.endpoint

    val authorization = try {
        RestClient.create()
            .post()
            .uri(uri + endpoint)
            .body(transaction)
            .retrieve()
            .toEntity(Authorization::class.java)
    } catch (e: Exception) {
        return err(AuthorizationError.UNSTABLE_AUTHORIZATION_SERVICE)
    }

    val body = authorization.body ?: return err(AuthorizationError.INVALID_AUTHORIZATION_RESPONSE)

    if (body.error != null) {
        return err(body.error.build())
    }

    return ok(body.authorized)

}
