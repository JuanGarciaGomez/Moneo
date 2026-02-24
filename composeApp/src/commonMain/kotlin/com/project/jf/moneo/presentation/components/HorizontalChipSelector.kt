package com.project.jf.moneo.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> HorizontalChipSelector(
    items: List<T>,
    selectedId: String?,
    onSelect: (String) -> Unit,
    onAdd: () -> Unit,
    addLabel: String,
    itemId: (T) -> String,
    itemLabel: (T) -> String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { item ->
                val id = itemId(item)
                val isSelected = selectedId == id
                SelectableChip(
                    label = itemLabel(item),
                    isSelected = isSelected,
                    onClick = { onSelect(id) }
                )
            }
        }

        Text(
            text = addLabel,
            style = TextStyle(
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .align(Alignment.End)
                .clickable { onAdd() }
        )
    }
}