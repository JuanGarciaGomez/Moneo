package com.project.jf.moneo.presentation.features.add_transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.jf.moneo.domain.model.PaymentMethod
import com.project.jf.moneo.domain.model.TransactionCategory
import com.project.jf.moneo.domain.model.TransactionType
import com.project.jf.moneo.presentation.components.DatePickerField
import com.project.jf.moneo.presentation.components.HorizontalChipSelector
import com.project.jf.moneo.presentation.components.TitleField
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.arrow_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel = koinViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    AddTransactionBottomSheet(
        state = state, 
        onIntent = { intent ->
            viewModel.handleIntent(intent)
            if (intent is AddTransactionIntent.OnDismiss) {
                onDismiss()
            }
        }
    )
}

@Composable
fun AddTransactionBottomSheet(
    state: AddTransactionUiState,
    onIntent: (AddTransactionIntent) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onIntent(AddTransactionIntent.OnDismiss) },
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 4.dp)
                    .width(40.dp)
                    .height(4.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
            )
        },
    ) {
        AddTransactionBottomSheetContent(
            state = state,
            onIntent = onIntent
        )

    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun AddTransactionBottomSheetContent(
    state: AddTransactionUiState,
    onIntent: (AddTransactionIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TransactionHeader(onIntent = onIntent)

        AmountDisplay(amount = state.transactionState.amount.toString())

        HorizontalChipSelector(
            items = TransactionType.entries.map { transactionType -> transactionType.name.lowercase() }
                .toList(),
            selectedId = state.transactionState.type?.name,
            onSelect = { },
            onAdd = {},
            addLabel = null,
            itemId = { it },
            itemLabel = { it }
        )

        TransactionSectionLabel(label = "Categoría")

        HorizontalChipSelector(
            items = TransactionCategory.entries.map { transactionCategory -> transactionCategory.name.lowercase() }
                .toList(),
            selectedId = state.transactionState.category?.name,
            onSelect = { },
            onAdd = {},
            addLabel = "Agregar nueva categoría",
            itemId = { it },
            itemLabel = { it }
        )

        TransactionSectionLabel(label = "Método de pago")

        HorizontalChipSelector(
            items = PaymentMethod.entries.map { transactionCategory -> transactionCategory.name.lowercase() }
                .toList(),
            selectedId = state.transactionState.paymentMethod?.name,
            onSelect = { },
            onAdd = {},
            addLabel = "Agregar nuevo método de pago",
            itemId = { it },
            itemLabel = { it }
        )

        TransactionSectionLabel(label = "Fecha de la transacción")
        DatePickerField(
            label = "Fecha",
            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            onDateSelected = { }
        )

        TransactionSectionLabel(label = "Título / nota")
        TitleField(
            value = state.transactionState.notes.orEmpty(),
            onValueChange = {  }
        )

        AddButton(onClick = onIntent)

    }
}

@Composable
private fun TransactionHeader(onIntent: (AddTransactionIntent) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Agregar transacción",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        IconButton(
            onClick = { onIntent(AddTransactionIntent.OnDismiss) },
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
        ) {
            Icon(
                painterResource(Res.drawable.arrow_back),
                contentDescription = "Seleccionar fecha",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun AmountDisplay(amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$ ",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        Text(
            text = amount,
            style = TextStyle(
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(36.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
private fun TransactionSectionLabel(label: String) {
    Text(
        text = label,
        style = TextStyle(
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
        )
    )
}

@Composable
private fun AddButton(onClick: (AddTransactionIntent) -> Unit) {
    Button(
        onClick = { onClick(AddTransactionIntent.OnAdd) },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(14.dp),
    ) {
        Text(
            text = "Agregar",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun AddTransactionContentPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 16.dp)
        ) {
            AddTransactionBottomSheetContent(
                state = AddTransactionUiState(),
                onIntent = {}
            )
        }
    }
}
