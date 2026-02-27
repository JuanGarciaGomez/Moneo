package com.project.jf.moneo.presentation.features.add_transaction

import com.project.jf.moneo.presentation.model.TransactionUI

data class AddTransactionUiState(
    val transactionState: TransactionUI = TransactionUI(amount = 0.0),
    val enableBtn: Boolean = false
)


sealed interface AddTransactionIntent {
    object OnDismiss: AddTransactionIntent
    object OnAdd: AddTransactionIntent
    object OnAddNewCategory: AddTransactionIntent
    object OnAddNewPaymentMethod: AddTransactionIntent
}



