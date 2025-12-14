package com.project.jf.moneo.domain.usecase

import com.project.jf.moneo.data.local.repository.control_period.ControlPeriodRepository
import com.project.jf.moneo.domain.model.ControlPeriod
import toData

class SaveControlPeriodUseCase(private val controlPeriodRepository: ControlPeriodRepository) {
    suspend operator fun invoke(controlPeriod: ControlPeriod) {
        controlPeriodRepository.insertPeriod(controlPeriod.toData())
    }
}
