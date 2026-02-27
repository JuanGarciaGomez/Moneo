package com.project.jf.moneo.presentation.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.project.jf.moneo.domain.model.PaymentMethod
import com.project.jf.moneo.domain.model.TransactionCategory
import com.project.jf.moneo.domain.model.TransactionType
import com.project.jf.moneo.presentation.DashboardRouterPreview
import com.project.jf.moneo.presentation.extensions.toShortDateEs
import com.project.jf.moneo.presentation.model.ControlPeriodUI
import com.project.jf.moneo.presentation.model.TransactionUI
import kotlinx.coroutines.flow.collectLatest
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.arrow_drop_down
import moneo.composeapp.generated.resources.calendar
import moneo.composeapp.generated.resources.dashboard_available
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel(), onNavigateToHistory: () -> Unit) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                else -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.handleIntent(HomeIntent.FetchData)
    }

    HomeContent(state, viewModel::handleIntent, onNavigateToHistory)
}

@Composable
fun HomeContent(
    state: HomeState,
    handleIntent: (HomeIntent) -> Unit,
    onNavigateToHistory: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ControlPeriodSelector(
                controlPeriods = state.allPeriods,
                selected = state.periodSelected?.name.orEmpty()
            ) {
                handleIntent(HomeIntent.PeriodSelected(it))
            }

            SummaryCard()
            HistoryCard(state.transactions, onNavigateToHistory)
        }
    }
}

@Composable
fun HistoryCard(transactions: List<TransactionUI>?, onNavigateToHistory: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Transacciones reciente", modifier = Modifier.weight(1f))
        Surface(onClick = onNavigateToHistory) {
            Text(
                text = "Ver todas",
                modifier = Modifier.padding(start = 8.dp),
                textAlign = TextAlign.End,
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(transactions.orEmpty()) { transaction ->
            ItemCard(transaction)
        }
    }
}

@Composable
fun ItemCard(transaction: TransactionUI) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        tonalElevation = 1.dp,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.calendar),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transaction.category?.displayName.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                val notes = if (!transaction.notes.isNullOrBlank())" - ${transaction.notes}" else ""
                Text(
                    text = "${transaction.date?.toShortDateEs()}$notes",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = transaction.amount.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = stringResource(Res.string.dashboard_available))
            Text(text = "$1,240.50")
            Text(text = "Para nombre de la categoria Mock")
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Ingresos")
                Text(text = "$1,500.00")
            }
        }

        Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Gastos")
                Text(text = "$259.50")
            }
        }
    }
}

@Composable
fun ControlPeriodSelector(
    controlPeriods: List<ControlPeriodUI>?,
    selected: String,
    onOptionSelected: (ControlPeriodUI) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Surface(
            modifier = Modifier
                .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth(),
            shape = RoundedCornerShape(30),
            color = Color.White,
            tonalElevation = 1.dp,
            shadowElevation = 2.dp,
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = selected,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(Res.drawable.arrow_drop_down),
                    contentDescription = null
                )
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            controlPeriods?.forEach { controlPeriod ->
                DropdownMenuItem(
                    text = { Text(text = controlPeriod.name) },
                    onClick = {
                        onOptionSelected(controlPeriod)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    DashboardRouterPreview{
        HomeContent(
            state = HomeState(
                allPeriods = listOf(
                    ControlPeriodUI(
                        id = 1,
                        name = "Nov-Dic",
                        startDate = 120L,
                        endDate = null
                    )
                ),
                periodSelected = ControlPeriodUI(
                    id = 1,
                    name = "Nov-Dic",
                    startDate = 120L,
                    endDate = null
                ),
                transactions = listOf(
                    TransactionUI(
                        id = 1,
                        controlPeriodId = 1L,
                        amount = 10000.0,
                        date = 20440L,
                        type = TransactionType.EXPENSE,
                        category = TransactionCategory.FOOD,
                        paymentMethod = PaymentMethod.DIGITAL_WALLET,
                        notes = "Perro caliente"
                    ),
                    TransactionUI(
                        id = 1,
                        controlPeriodId = 1L,
                        amount = 15000.0,
                        date = 20441L,
                        type = TransactionType.EXPENSE,
                        category = TransactionCategory.SHOPPING,
                        paymentMethod = PaymentMethod.SAVINGS_ACCOUNT,
                    ),
                    TransactionUI(
                        id = 1,
                        controlPeriodId = 1L,
                        amount = 1500000.0,
                        date = 20442L,
                        type = TransactionType.EXPENSE,
                        category = TransactionCategory.EDUCATION,
                        paymentMethod = PaymentMethod.CREDIT_CARD,
                    )
                )
            ),
            handleIntent = {},
            onNavigateToHistory = {}
        )
    }
}
