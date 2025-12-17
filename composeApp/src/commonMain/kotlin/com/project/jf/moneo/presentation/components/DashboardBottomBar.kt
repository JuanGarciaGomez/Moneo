package com.project.jf.moneo.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.jf.moneo.presentation.model.BottomNavigationItems
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardBottomBar(
    bottomNavSelected: BottomNavigationItems,
    onBottomNavSelected: (item: BottomNavigationItems) -> Unit
) {
    val items = BottomNavigationItems.entries.toList()

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 2.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = bottomNavSelected == item,
                onClick = { onBottomNavSelected(item) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = stringResource(item.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.title),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun DashboardBottomBarPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        DashboardBottomBar(bottomNavSelected = BottomNavigationItems.HOME, onBottomNavSelected = {})
    }
}