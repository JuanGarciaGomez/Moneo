package com.project.jf.moneo.data.local.repository.transaction

import com.project.jf.moneo.data.local.dao.TransactionDao
import com.project.jf.moneo.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao
) : TransactionRepository {
    override suspend fun insertTransaction(transaction: TransactionEntity) {
        transactionDao.insertTransaction(transaction)
    }

    override fun getTransactionsForPeriod(periodId: Long): Flow<List<TransactionEntity>> {
        return transactionDao.getTransactionsForPeriod(periodId)
    }

    override fun getTotalIncome(periodId: Long): Flow<Double?> {
        return transactionDao.getTotalIncome(periodId)
    }

    override fun getTotalExpenses(periodId: Long): Flow<Double?> {
        return transactionDao.getTotalExpenses(periodId)
    }

    override fun getCreditCardExpenses(periodId: Long): Flow<Double?> {
        return transactionDao.getCreditCardExpenses(periodId)
    }
}

