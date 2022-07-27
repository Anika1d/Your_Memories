package com.template.ym.composable.screens.memories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.bar.BottomBar
import com.template.ym.composable.screens.bar.Toolbar
import com.template.ym.composable.screens.memories.modeopenscreen.ModeOpenScreen
import com.template.ym.ui.theme.BackgroundIconColor
import com.template.ym.ui.theme.RoyalPurple
import com.template.ym.ui.theme.TextPrimaryColor


@Composable
fun ListMemories(modifier: Modifier, navController: NavHostController) {
    Column() {
        Toolbar(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            title = "Воспоминания",
            navController,
            true,
            contentDropDownMenu = {
                DropdownMenuItem(text = { Text(text = "Создать") }, onClick = {
                    navController.navigate(Route.ChangeOrCreateMemoriesScreen.route + ModeOpenScreen.Create.mode)
                })
            })
        LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(21) {
                ItemListMemory(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(RoyalPurple),
                    navController = navController
                )
            }
        }
        BottomBar(
            Modifier
                .fillMaxWidth(1f)
                .height(40.dp)
                .background(Color.Black)
        )
    }
}

@Composable
fun ItemListMemory(modifier: Modifier, navController: NavHostController) {
    Column(
        modifier = modifier.clickable {
            navController.navigate(Route.InfoMemoriesScreen.route)
        },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(Modifier.fillMaxSize()) {
            Text(text = "DR Kirilla", color = TextPrimaryColor)
        }
        Row(Modifier.fillMaxSize(), Arrangement.SpaceBetween) {
            Text(text = "MOI PARNI", color = TextPrimaryColor)
            Text(text = "11.12.2011", color = TextPrimaryColor)
        }
    }
}
