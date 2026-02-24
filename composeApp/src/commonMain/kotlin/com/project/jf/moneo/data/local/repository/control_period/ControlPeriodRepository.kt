package com.project.jf.moneo.data.local.repository.control_period

import com.project.jf.moneo.data.local.entity.ControlPeriodEntity
import kotlinx.coroutines.flow.Flow

interface ControlPeriodRepository {
    suspend fun insertPeriod(period: ControlPeriodEntity): Long
    fun getAllPeriods(): Flow<List<ControlPeriodEntity>>
    suspend fun getPeriodById(periodId: Long): ControlPeriodEntity?
}