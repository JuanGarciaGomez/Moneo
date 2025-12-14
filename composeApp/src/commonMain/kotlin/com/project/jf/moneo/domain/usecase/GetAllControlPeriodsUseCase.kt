package com.project.jf.moneo.domain.usecase

import com.project.jf.moneo.data.local.repository.control_period.ControlPeriodRepository

class GetAllControlPeriodsUseCase(private val controlPeriodRepository: ControlPeriodRepository) {
    operator fun invoke() = controlPeriodRepository.getAllPeriods()
}
