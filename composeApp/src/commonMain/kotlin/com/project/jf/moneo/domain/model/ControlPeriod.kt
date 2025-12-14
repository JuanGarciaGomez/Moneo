package com.project.jf.moneo.domain.model

data class ControlPeriod(
    val id: Long = 0,
    val name: String,
    val startDate: Long,
    val endDate: Long? = null
)