package com.mardev.calcolasconto.ui.info.view

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCard(cardInfo: CardInfo) {
    val uriHandler = LocalUriHandler.current
    val view = LocalView.current

    Card(
        onClick = {
            if (cardInfo.isVibrationEnabled) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            }
            cardInfo.uri?.let { uriHandler.openUri(it) }
        },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 16.dp)
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = cardInfo.icon, contentDescription = null)
            Text(cardInfo.title, fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(cardInfo.subtitle, fontSize = 18.sp, fontWeight = FontWeight.Light)
        }
    }
}