package com.project.jf.moneo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.arrow_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


data class AddTransactionState(
    val amount: String = "0.00",
    val selectedType: TransactionType = TransactionType.EXPENSE,
    val categories: List<TransactionCategory> = defaultCategories(),
    val selectedCategoryId: String? = defaultCategories().firstOrNull()?.id,
    val paymentMethods: List<PaymentMethod> = defaultPaymentMethods(),
    val selectedPaymentMethodId: String? = defaultPaymentMethods().firstOrNull()?.id,
    val titleNote: String = ""
)

fun defaultCategories() = listOf(
    TransactionCategory("food", "Comida y envíos"),
    TransactionCategory("transport", "Transporte"),
    TransactionCategory("rent", "Renta")
)

fun defaultPaymentMethods() = listOf(
    PaymentMethod("card", "Tarjeta principal"),
    PaymentMethod("bank", "Cuenta bancaria"),
    PaymentMethod("cash", "Efectivo")
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionBottomSheet(
    onDismiss: () -> Unit,
    onAddTransaction: (AddTransactionState) -> Unit,
    onManageTransactionTypes: () -> Unit,
    onAddNewCategory: () -> Unit,
    onAddNewPaymentMethod: () -> Unit,
    modifier: Modifier = Modifier,
    initialState: AddTransactionState = AddTransactionState()
) {
    var state by remember { mutableStateOf(initialState) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
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
        modifier = modifier
    ) {
        AddTransactionContent(
            state = state,
            onStateChange = { state = it },
            onDismiss = onDismiss,
            onAdd = { onAddTransaction(state) },
            onManageTransactionTypes = onManageTransactionTypes,
            onAddNewCategory = onAddNewCategory,
            onAddNewPaymentMethod = onAddNewPaymentMethod
        )
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun AddTransactionContent(
    state: AddTransactionState,
    onStateChange: (AddTransactionState) -> Unit,
    onDismiss: () -> Unit,
    onAdd: () -> Unit,
    onManageTransactionTypes: () -> Unit,
    onAddNewCategory: () -> Unit,
    onAddNewPaymentMethod: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TransactionHeader(onDismiss = onDismiss)

        AmountDisplay(amount = state.amount)

        HorizontalChipSelector(
            items = TransactionType.entries.map { transactionType -> transactionType.name.lowercase() }
                .toList(),
            selectedId = state.selectedType.name,
            onSelect = { onStateChange(state.copy(selectedType = TransactionType.valueOf(it))) },
            onAdd = {},
            addLabel = "",
            itemId = { it },
            itemLabel = { it }
        )

        TransactionSectionLabel(label = "Categoría")
        HorizontalChipSelector(
            items = state.categories,
            selectedId = state.selectedCategoryId,
            onSelect = { onStateChange(state.copy(selectedCategoryId = it)) },
            onAdd = onAddNewCategory,
            addLabel = "Agregar nueva categoría",
            itemId = { it.id },
            itemLabel = { it.name }
        )

        TransactionSectionLabel(label = "Método de pago")
        HorizontalChipSelector(
            items = state.paymentMethods,
            selectedId = state.selectedPaymentMethodId,
            onSelect = { onStateChange(state.copy(selectedPaymentMethodId = it)) },
            onAdd = onAddNewPaymentMethod,
            addLabel = "Agregar nuevo método de pago",
            itemId = { it.id },
            itemLabel = { it.name }
        )

        TransactionSectionLabel(label = "Fecha de la transacción")
        DatePickerField(
            label = "Fecha",
            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            onDateSelected = { }
        )

        TransactionSectionLabel(label = "Título / nota")
        TitleField(
            value = state.titleNote,
            onValueChange = { onStateChange(state.copy(titleNote = it)) }
        )

        AddButton(onClick = onAdd)
    }
}

@Composable
private fun TransactionHeader(onDismiss: () -> Unit) {
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
            onClick = onDismiss,
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
private fun AddButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
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
            AddTransactionContent(
                state = AddTransactionState(),
                onStateChange = {},
                onDismiss = {},
                onAdd = {},
                onManageTransactionTypes = {},
                onAddNewCategory = {},
                onAddNewPaymentMethod = {}
            )
        }
    }
}