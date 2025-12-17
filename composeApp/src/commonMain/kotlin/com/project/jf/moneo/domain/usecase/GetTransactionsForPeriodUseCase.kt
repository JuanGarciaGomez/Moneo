package com.project.jf.moneo.domain.usecase

import com.project.jf.moneo.data.local.entity.toDomain
import com.project.jf.moneo.data.local.repository.transaction.TransactionRepository
import com.project.jf.moneo.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTransactionsForPeriodUseCase(private val transactionRepository: TransactionRepository) {
    operator fun invoke(periodId: Long): Flow<List<Transaction>> {
        return transactionRepository.getTransactionsForPeriod(periodId).map { listOfEntities ->
            listOfEntities.map { entity -> entity.toDomain() }
        }
    }
}
