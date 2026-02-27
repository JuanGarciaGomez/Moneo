package com.project.jf.moneo.domain.model

data class Transaction(
    val id: Long? = null,
    val controlPeriodId: Long? = null,
    val amount: Double? = null,
    val date: Long? = null,
    val type: TransactionType? = null,
    val category: TransactionCategory? = null,
    val paymentMethod: PaymentMethod? = null,
    val notes: String? = null
)
