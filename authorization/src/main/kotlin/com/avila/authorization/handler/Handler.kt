package com.avila.authorization.handler

import com.avila.authorization.model.Transaction

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController class Handler {

    @PostMapping("/authorize")
    fun authorize(@RequestBody transaction: Transaction) {

    }

}
