package com.project.jf.moneo.data.local.repository.transaction

import com.project.jf.moneo.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun insertTransaction(transaction: TransactionEntity)
    fun getTransactionsForPeriod(periodId: Long): Flow<List<TransactionEntity>>
    fun getTotalIncome(periodId: Long): Flow<Double?>
    fun getTotalExpenses(periodId: Long): Flow<Double?>
    fun getCreditCardExpenses(periodId: Long): Flow<Double?>
}