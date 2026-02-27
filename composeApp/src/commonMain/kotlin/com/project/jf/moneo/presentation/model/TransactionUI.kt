package com.project.jf.moneo.presentation.model

import com.project.jf.moneo.domain.model.PaymentMethod
import com.project.jf.moneo.domain.model.Transaction
import com.project.jf.moneo.domain.model.TransactionCategory
import com.project.jf.moneo.domain.model.TransactionType

data class TransactionUI(
    val id: Long = 0,
    val controlPeriodId: Long,
    val amount: Double,
    val date: Long,
    val type: TransactionType,
    val category: TransactionCategory,
    val paymentMethod: PaymentMethod,
    val notes: String? = null
)

fun TransactionUI.toDomain(): Transaction {
    return Transaction(
        id = id,
        controlPeriodId = controlPeriodId,
        amount = amount,
        date = date,
        type = type,
        category = category,
        paymentMethod = paymentMethod,
        notes = notes
    )
}

fun Transaction.toUI(): TransactionUI {
    return TransactionUI(
        id = id,
        controlPeriodId = controlPeriodId,
        amount = amount,
        date = date,
        type = type,
        category = category,
        paymentMethod = paymentMethod,
        notes = notes
    )
}
