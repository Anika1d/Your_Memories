package com.template.ym.composable.screens.memories

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.bar.BottomBar
import com.template.ym.composable.screens.bar.Toolbar
import com.template.ym.composable.screens.memories.modeopenscreen.ModeOpenScreen
import com.template.ym.ui.theme.ParadisePink
import com.template.ym.ui.theme.TextPrimaryColor

@Composable
fun InfoMemories(modifier: Modifier, navController: NavHostController) {
    Column {
        Toolbar(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.Black), title = "DR KIrrila",
            navController = navController,
            back_flag = true
        ) {
            DropdownMenuItem(
                text = { Text(text = "Отметить") },
                onClick = { /*TODO*/ })
            DropdownMenuItem(
                text = { Text(text = "Забыть") },
                onClick = { /*TODO*/ })
            /**TODO В будущем проверка на свой профиль if ()*/
            Divider()
            DropdownMenuItem(
                text = { Text(text = "Изменить") },

                onClick = {
                    navController.navigate(Route.ChangeOrCreateMemoriesScreen.route + ModeOpenScreen.Update.mode)
                })
        }
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.BottomCenter
            ) {
                AsyncImage(
                    model = R.drawable.tmp_foto_memory,
                    contentDescription = null,
                    modifier = Modifier
                        .width(
                            (Resources.getSystem().displayMetrics.widthPixels / 2.7).dp
                        )
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x4D888888)),
                    text = "Audio",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = TextPrimaryColor,
                        fontSize = 22.sp
                    ),
                )
            }
            Text(
                text = "OPISANIE OPISANIE OPISANIE OPISANIE " +
                        "OPISANIE OPISANIE OPISANIE OPISANIE OPISANIE OPISANIE OPISANIE OPISANIE"
            )
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
            {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ParadisePink,
                        contentColor = TextPrimaryColor
                    ),
                    shape = RoundedCornerShape(10f),
                    modifier = Modifier.fillMaxWidth(0.7f),
                    content = {
                        Text(text = "Напомнить")
                    }
                )
            }

        }
        BottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Black)
        )
    }

}
