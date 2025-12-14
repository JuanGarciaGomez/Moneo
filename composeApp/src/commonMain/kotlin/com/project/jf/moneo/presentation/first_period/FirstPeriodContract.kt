package com.project.jf.moneo.presentation.first_period

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
data class FirstPeriodState(
    val periodName: String = "",
    val startDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    val endDate: LocalDate? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val canProceed: Boolean = false
)

sealed interface FirstPeriodIntent {
    data class UpdatePeriodName(val name: String) : FirstPeriodIntent
    data class UpdateStartDate(val date: LocalDate) : FirstPeriodIntent
    data class UpdateEndDate(val date: LocalDate?) : FirstPeriodIntent
    object SavePeriod : FirstPeriodIntent
    object NavigateBack : FirstPeriodIntent
}

sealed interface FirstPeriodEffect {
    object NavigateToNextScreen : FirstPeriodEffect
    object NavigateBack : FirstPeriodEffect
}