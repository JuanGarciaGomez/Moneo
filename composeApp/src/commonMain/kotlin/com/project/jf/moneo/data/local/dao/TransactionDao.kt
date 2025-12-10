package com.project.jf.moneo.data.local.dao

import androidx.room.*
import com.project.jf.moneo.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    // Inserta una nueva transacción
    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity)

    // Obtiene todas las transacciones asociadas a un periodo de control
    @Query("SELECT * FROM transactions WHERE controlPeriodId = :periodId ORDER BY date DESC")
    fun getTransactionsForPeriod(periodId: Long): Flow<List<TransactionEntity>>

    // --- Consultas de Reporte (La clave de la app) ---

    // Calcula el total de ingresos para un periodo específico
    @Query("""
        SELECT SUM(amount) 
        FROM transactions 
        WHERE controlPeriodId = :periodId AND type = 'INCOME'
    """)
    fun getTotalIncome(periodId: Long): Flow<Double?>

    // Calcula el total de gastos para un periodo específico
    // Usamos el enum 'EXPENSE' que definimos previamente
    @Query("""
        SELECT SUM(amount) 
        FROM transactions 
        WHERE controlPeriodId = :periodId AND type = 'EXPENSE'
    """)
    fun getTotalExpenses(periodId: Long): Flow<Double?>

    // Opcional: Obtener los gastos por tarjeta de crédito (simulando "Gastos TC" de tu imagen)
    @Query("""
        SELECT SUM(amount) 
        FROM transactions 
        WHERE controlPeriodId = :periodId 
        AND type = 'EXPENSE' 
        AND paymentMethod = 'Tarjeta' 
    """)
    fun getCreditCardExpenses(periodId: Long): Flow<Double?>
}