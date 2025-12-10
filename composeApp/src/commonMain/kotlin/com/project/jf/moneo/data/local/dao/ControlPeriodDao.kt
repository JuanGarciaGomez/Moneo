package com.project.jf.moneo.data.local.dao

import ControlPeriodEntity
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ControlPeriodDao {

    // Inserta un nuevo periodo de control (el ID Long se autogenera)
    @Insert
    suspend fun insertPeriod(period: ControlPeriodEntity): Long

    // Obtiene todos los periodos de control para mostrarlos en una lista
    // Flow es ideal para Compose, ya que se actualiza automáticamente
    @Query("SELECT * FROM control_periods ORDER BY startDate DESC")
    fun getAllPeriods(): Flow<List<ControlPeriodEntity>>

    // Obtiene un periodo por su ID
    @Query("SELECT * FROM control_periods WHERE id = :periodId")
    suspend fun getPeriodById(periodId: Long): ControlPeriodEntity?
}