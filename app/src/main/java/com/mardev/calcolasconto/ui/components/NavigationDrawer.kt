package com.mardev.calcolasconto.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mardev.calcolasconto.ui.navigation.CalcolaScontoDestinations
import com.mardev.calcolasconto.ui.navigation.calcolaScontoScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    onNavigationDrawerItemClick: (itemClicked: CalcolaScontoDestinations) -> Unit,
    content: @Composable () -> Unit,
    scope: CoroutineScope,
    drawerState: DrawerState,
    selectedItemIndex: Int
) {

    val items = calcolaScontoScreens

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                val context = LocalContext.current
                NavigationDrawerHeader(context = context)
                Spacer(Modifier.height(24.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) item.selectedIcon
                                else item.unselectedIcon, contentDescription = stringResource(
                                    id = item.stringResourceId
                                )
                            )
                        },
                        label = { Text(text = stringResource(item.stringResourceId)) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            scope.launch { drawerState.close() }
                            onNavigationDrawerItemClick(item)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = content,
        gesturesEnabled = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationDrawerHeader(context: Context) {
    Spacer(Modifier.height(12.dp))
    Column(Modifier.padding(start = 24.dp)) {
        Text(text = "Calcola sconto", fontSize = 24.sp, fontWeight = FontWeight.ExtraLight)
        Text(text = "By Mardev",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .combinedClickable(onLongClick = {
                    Toast.makeText(context, "Easter egg trovato 🐣", Toast.LENGTH_SHORT).show()
                })
                {

                }
        )
    }
}