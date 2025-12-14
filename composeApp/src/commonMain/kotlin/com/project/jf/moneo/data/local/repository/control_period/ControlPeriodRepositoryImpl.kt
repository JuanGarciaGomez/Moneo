package com.project.jf.moneo.data.local.repository.control_period

import ControlPeriodEntity
import com.project.jf.moneo.data.local.dao.ControlPeriodDao
import kotlinx.coroutines.flow.Flow

class ControlPeriodRepositoryImpl(
    private val controlPeriodDao: ControlPeriodDao
) : ControlPeriodRepository {

    override suspend fun insertPeriod(period: ControlPeriodEntity): Long {
        return controlPeriodDao.insertPeriod(period)
    }

    override fun getAllPeriods(): Flow<List<ControlPeriodEntity>> {
        return controlPeriodDao.getAllPeriods()
    }

    override suspend fun getPeriodById(periodId: Long): ControlPeriodEntity? {
        return controlPeriodDao.getPeriodById(periodId)
    }
}

