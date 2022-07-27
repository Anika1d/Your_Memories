package com.template.ym.composable.screens.profile

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.bar.BottomBar
import com.template.ym.composable.screens.bar.Toolbar
import com.template.ym.ui.theme.*

@Composable
fun ProfileScreen(modifier: Modifier, navController: NavHostController) {
    Column() {

        Toolbar(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp), title = "Андрей Петров"
        ) {
            DropdownMenuItem(text = { Text(text = "ID") }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "QR") }, onClick = { /*TODO*/ })
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.settings)) },
                onClick = { /*TODO*/ })
            /**TODO В будущем проверка на свой профиль if ()*/
            Divider()
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.exit)) },
                onClick = { navController.navigate(Route.SigInScreen.route) })
        }
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                modifier = Modifier.height(120.dp),
            ) {
                AsyncImage(
                    model = R.drawable.tmpavatar, contentDescription = "avatar",

                    )
                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(8.dp)) {
                    Row() {
                        Text(color = Color.White, text = "Жизнь словна жизнь, как жизнь жизни. ")
                    }
                }
            }
            /** TODO if Позже проверка на свой профиль*/
//        TextButton(
//            modifier = Modifier.fillMaxWidth(),
//
//            onClick = { /*TODO*/ },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = ParadisePink,
//                contentColor = Color.White
//            ),
//            shape = RectangleShape
//
//        ) {
//            Text(color = Color.White, text = "Редактировать")
//        }
            /*else*/    Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            content = {
                TextButton(
                    modifier = Modifier.fillMaxWidth(0.5f),

                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ParadisePink,
                        contentColor = Color.White
                    ),
                    shape = RectangleShape

                ) {
                    Text(color = Color.White, text = "Сообщения")
                }
                TextButton(
                    modifier = Modifier.fillMaxWidth(0.99f),

                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                        containerColor = ParadisePink,
                        contentColor = Color.White
                    ),
                    shape = RectangleShape
                ) {
                    Text(color = Color.White, text = "Забыть")
                }
            }
        )
            Text(text = "Воспоминания", fontSize = 22.sp, color = Color.White)

            /**Mode visibility  Grid OR Row*/
            var modeVisibility by
            remember {
                mutableStateOf(true)
            }
            Box() {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        IconButton(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(4.dp),
                            onClick = { modeVisibility = true },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = ParadisePink,
                                contentColor = Color.White
                            ),

                            ) {
                            Icon(
                                modifier = Modifier.padding(4.dp),
                                painter = painterResource(id = R.drawable.ic_rect_grid),
                                contentDescription = null
                            )
                        }
                        IconButton(
                            modifier = Modifier.fillMaxWidth(0.99f),
                            onClick = { modeVisibility = false },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = ParadisePink,
                                contentColor = Color.White
                            ),
                        ) {
                            Icon(
                                modifier = Modifier.padding(4.dp),
                                painter = painterResource(id = R.drawable.ic_column_grid),
                                contentDescription = null
                            )
                        }
                    }
                )
            }
            if (modeVisibility)
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f), horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp), content = {
                        items(11) {
                            ItemsMemory(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .border(4.dp, color = Saffron)
                            )
                        }
                    })
            else LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                horizontalArrangement = Arrangement.spacedBy(4.dp), content = {
                    items(3) {
                        ItemsMemory(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .border(4.dp, color = Saffron)

                        )
                    }
                })
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
            {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ParadisePink,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10f),
                    modifier = Modifier.fillMaxWidth(0.7f),
                    content = {
                        Text(text = stringResource(id = R.string.create_memories))
                    }
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
fun ItemsMemory(modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.TopCenter) {

        AsyncImage(
            model = R.drawable.tmp_foto_memory,
            contentDescription = null,
            modifier = Modifier
                .width(
                    (Resources.getSystem().displayMetrics.widthPixels / 2.7).dp
                )
        )

        Row(
            modifier = Modifier
                .width(
                    (Resources.getSystem().displayMetrics.widthPixels / 2.7).dp
                )
                .background(RoyalPurple), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .width(
                        (Resources.getSystem().displayMetrics.widthPixels / 2.7).dp
                    ),
                text = "ДР Кирилла",
                style = TextStyle(
                    color = Saffron,
                    fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,

                    ), textAlign = TextAlign.Center
            )
        }
    }
}

//
//@Preview
//@Composable
//fun PreviewProfileScreen() {
//    ProfileScreen(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BackgroundIconColor)
//            .padding(4.dp),
//        navController = navController
//    )
//}
//
