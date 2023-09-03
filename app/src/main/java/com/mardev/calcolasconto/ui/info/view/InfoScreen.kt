package com.mardev.calcolasconto.ui.info.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoScreen() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        item {
            MyCard(
                "Aiuta con le traduzioni",
                "Apri una richiesta di modifica su GitHub",
                Icons.Default.Translate,
                "https://google.com"
            )
        }
        item {
            MyCard(
                "Feedback e richieste",
                "Questo è un progetto open-source",
                Icons.Default.Feedback,
                "https://google.com"
            )
        }
        item {
            MyCard(
                "Versione app",
                "1.0.0",
                Icons.Default.SystemUpdate,
                null
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyCard(title: String, subtitle: String, icon: ImageVector, uri: String?) {
    val uriHandler = LocalUriHandler.current

    Card(
        onClick = {
            if (uri!=null){
                uriHandler.openUri(uri)
            }
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
            Icon(imageVector = icon, contentDescription = null)
            Text(title, fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(subtitle, fontSize = 18.sp, fontWeight = FontWeight.Light)
        }
    }
}