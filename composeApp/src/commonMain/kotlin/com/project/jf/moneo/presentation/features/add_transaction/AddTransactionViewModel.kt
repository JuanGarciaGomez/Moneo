package com.project.jf.moneo.presentation.features.add_transaction

import androidx.lifecycle.ViewModel
import com.project.jf.moneo.presentation.features.first_period.FirstPeriodIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddTransactionViewModel: ViewModel() {

    private val _state = MutableStateFlow(AddTransactionUiState())
    val state = _state.asStateFlow()

    fun handleIntent(intent: AddTransactionIntent) {
        when (intent) {
            AddTransactionIntent.OnDismiss -> {}
            else -> {}
        }
    }

}