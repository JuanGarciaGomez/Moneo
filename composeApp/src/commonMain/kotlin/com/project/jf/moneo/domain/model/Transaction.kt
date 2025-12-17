package com.project.jf.moneo.domain.model

data class Transaction(
    val id: Long = 0,
    val controlPeriodId: Long,
    val title: String,
    val amount: Double,
    val date: Long,
    val type: TransactionType,
    val category: String,
    val paymentMethod: String,
    val notes: String? = null
)
