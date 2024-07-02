package com.avila.authorization.service

import com.github.michaelbull.result.Err as err
import com.github.michaelbull.result.Ok as ok
import com.github.michaelbull.result.Result as r

import com.avila.authorization.error.APIError as Error
import com.avila.authorization.error.TransactionError
import com.avila.authorization.model.Transaction
import com.avila.authorization.model.canPay
import com.avila.authorization.model.isCommon
import com.avila.authorization.repository.CustomerRepository

import org.springframework.stereotype.Service

@Service class AuthorizationService ( private val repository: CustomerRepository ) {

    fun validate(transaction: Transaction): r<Boolean, Error> {

        val payer = repository.findById(transaction.payer)
            ?: return err(TransactionError.PAYER_NOT_FOUND)

        val payee = repository.findById(transaction.payee)
            ?: return err(TransactionError.PAYEE_NOT_FOUND)

        if (payer.id?.equals(payee.id) == true)
            return err (
                TransactionError.INVALID_TRANSACTION
                    .message("Invalid transaction - Payer and payee are the same")
            )

        if (!payer.canPay(transaction.value))
            return err (
                TransactionError.INVALID_TRANSACTION
                    .message("Invalid transaction - Insufficient payer's balance")
            )

        if (!payer.isCommon())
            return err (
                TransactionError.INVALID_TRANSACTION
                    .message("Invalid transaction - Payer is not a common customer")
            )

        return ok (
            true
        )

    }

}
