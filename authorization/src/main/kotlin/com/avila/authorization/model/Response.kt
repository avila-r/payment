package com.avila.authorization.model

import com.avila.authorization.error.APIErrorResponse

data class Authorization (
    val authorized: Boolean,
    val error: APIErrorResponse?
)
