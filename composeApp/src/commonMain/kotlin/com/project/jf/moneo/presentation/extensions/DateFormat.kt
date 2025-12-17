package com.project.jf.moneo.presentation.extensions

import com.project.jf.moneo.presentation.DateConstants.SHORT_MONTHS_ES
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

fun Long.toShortDateEs(): String {
    val date = LocalDate.fromEpochDays(this.toInt())
    val month = SHORT_MONTHS_ES.getOrElse(date.month.number - 1) { "" }
    return "${date.day} $month ${date.year}"
}

