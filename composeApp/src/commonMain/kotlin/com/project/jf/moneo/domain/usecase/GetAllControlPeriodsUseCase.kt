package com.project.jf.moneo.domain.usecase

import com.project.jf.moneo.data.local.entity.toDomain
import com.project.jf.moneo.data.local.repository.control_period.ControlPeriodRepository
import com.project.jf.moneo.domain.model.ControlPeriod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllControlPeriodsUseCase(private val controlPeriodRepository: ControlPeriodRepository) {
    operator fun invoke(): Flow<List<ControlPeriod>> {
        return controlPeriodRepository.getAllPeriods().map { listOfEntities ->
            listOfEntities.map { entity -> entity.toDomain() }
        }
    }
}
