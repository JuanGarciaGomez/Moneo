package com.project.jf.moneo.domain.model

data class Transaction(
    val id: Long = 0,
    val controlPeriodId: Long,
    val amount: Double,
    val date: Long,
    val type: TransactionType,
    val category: TransactionCategory,
    val paymentMethod: PaymentMethod,
    val notes: String? = null
)
