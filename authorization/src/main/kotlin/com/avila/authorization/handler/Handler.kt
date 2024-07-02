package com.avila.authorization.handler

import com.avila.authorization.error.response
import com.avila.authorization.model.Authorization
import com.avila.authorization.model.Transaction
import com.avila.authorization.service.AuthorizationService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController class Handler ( private val service: AuthorizationService ) {

    @PostMapping("/authorize")
    fun authorize(@RequestBody transaction: Transaction): ResponseEntity<Authorization> {

        val result = service.validate(transaction)

        if (result.isOk && result.value) {
            return ResponseEntity
                .ok(
                    Authorization (
                        authorized = true,
                        error = null
                    )
                )
        }

        return ResponseEntity
            .ok(
                Authorization (
                    authorized = false,
                    error = result.error.response()
                )
            )

    }

}
