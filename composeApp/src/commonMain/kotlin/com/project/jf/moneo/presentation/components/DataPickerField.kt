
package com.project.jf.moneo.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.calendar
import org.jetbrains.compose.resources.painterResource
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Composable
fun DatePickerField(
    label: String,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    var showModal by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedDate?.toString() ?: "",
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { showModal = true }) {
                Icon(
                    painterResource(Res.drawable.calendar),
                    contentDescription = "Seleccionar fecha"
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )

    if (showModal) {
        val dataPickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate?.atStartOfDayIn(timeZone = TimeZone.UTC)
                ?.toEpochMilliseconds()

        )
        DatePickerDialog(
            onDismissRequest = { showModal = false },
            confirmButton = {
                TextButton(onClick = {
                    dataPickerState.selectedDateMillis?.let { millis ->
                        val date = Instant.fromEpochMilliseconds(millis)
                            .toLocalDateTime(TimeZone.UTC)
                            .date
                        onDateSelected(date)
                    }
                    showModal = false
                }) {
                    Text("OK")
                }
            }, dismissButton = {
                TextButton(onClick = { showModal = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = dataPickerState)
        }
    }
}
