package com.mardev.calcolasconto.ui.info.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material.icons.filled.Translate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.mardev.calcolasconto.ui.settings.viewmodel.SettingsScreenState

@Composable
fun InfoScreen(
    appState: SettingsScreenState
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        val cards = listOf(
            CardInfo(
                title = "Aiuta con le traduzioni",
                subtitle = "Apri una richiesta di modifica su GitHub",
                icon = Icons.Default.Translate,
                uri = "https://google.com",
                isVibrationEnabled = appState.currentVibrationOption
            ),
            CardInfo(
                title = "Feedback e richieste",
                subtitle = "Questo è un progetto open-source",
                icon = Icons.Default.Feedback,
                uri = "https://google.com",
                isVibrationEnabled = appState.currentVibrationOption
            ),
            CardInfo(
                title = "Versione app",
                subtitle = "1.0.0",
                icon = Icons.Default.SystemUpdate,
                uri = null,
                isVibrationEnabled = appState.currentVibrationOption
            )
        )

        items(cards) { cardInfo ->
            MyCard(cardInfo)
        }
    }
}

data class CardInfo(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val uri: String?,
    val isVibrationEnabled: Boolean
)
