package com.project.jf.moneo.data.local.repository.control_period

import ControlPeriodEntity
import kotlinx.coroutines.flow.Flow

interface ControlPeriodRepository {
    suspend fun insertPeriod(period: ControlPeriodEntity): Long
    fun getAllPeriods(): Flow<List<ControlPeriodEntity>>
    suspend fun getPeriodById(periodId: Long): ControlPeriodEntity?
}