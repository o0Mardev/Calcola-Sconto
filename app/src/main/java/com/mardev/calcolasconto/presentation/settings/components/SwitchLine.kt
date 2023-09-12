package com.mardev.calcolasconto.presentation.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class SwitchInfo(
    val isChecked: Boolean, val title: String, val subTitle: String, val icon: ImageVector
)

@Composable
fun SwitchLine(switch: SwitchInfo, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = switch.icon, contentDescription = null)
            Column(
                Modifier.padding(start = 18.dp),
            ) {
                Text(text = switch.title, fontSize = 18.sp)
                Text(text = switch.subTitle)
            }
        }
        Switch(checked = switch.isChecked, onCheckedChange = onCheckedChange)
    }
}


