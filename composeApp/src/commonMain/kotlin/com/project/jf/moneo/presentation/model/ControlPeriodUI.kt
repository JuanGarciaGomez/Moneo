package com.project.jf.moneo.presentation.model

import com.project.jf.moneo.domain.model.ControlPeriod

data class ControlPeriodUI(
    val id: Long = 0,
    val name: String,
    val startDate: Long,
    val endDate: Long? = null
)

fun ControlPeriod.toUI(): ControlPeriodUI {
    return ControlPeriodUI(
        id = id,
        name = name,
        startDate = startDate,
        endDate = endDate
    )
}

fun ControlPeriodUI.toDomain(): ControlPeriod {
    return ControlPeriod(
        id = id,
        name = name,
        startDate = startDate,
        endDate = endDate
    )
}
