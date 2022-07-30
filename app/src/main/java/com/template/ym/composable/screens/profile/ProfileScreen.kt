package com.template.ym.composable.screens.profile

import android.content.res.Resources
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.template.core.model.memories.CoreMemories
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.bar.BottomBar
import com.template.ym.composable.screens.bar.Toolbar
import com.template.ym.composable.screens.memories.modeopenscreen.ModeOpenScreen
import com.template.ym.composable.tools.toBitmap
import com.template.ym.ui.theme.*
import com.template.ym.viewmodels.profile.ProfileViewModel

@Composable
fun ProfileScreen(modifier: Modifier, navController: NavHostController, id: Long) {
    val viewModel = viewModel(ProfileViewModel::class.java)
    var listMemories: List<CoreMemories>
    var backPressed by remember {
        mutableStateOf(0L)
    }
    val context = LocalContext.current
    viewModel.apply {
        initMemories(id)
        listMemories = viewModel.listMemories
    }
    Column() {
        Toolbar(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            title = "Андрей Петров",
            ///if тут будет проверка можно ли вернуться пока нет, тк нет друзей
            navigationIcon = {
                IconButton(onClick = {
                    if (backPressed + 2000 > System.currentTimeMillis()) {
                        viewModel.exit()
                        navController.navigate(Route.SigInScreen.route)
                    } else {
                        Toast.makeText(context, "Press again to exit", Toast.LENGTH_LONG).show()
                        backPressed = System.currentTimeMillis()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        modifier = Modifier.fillMaxHeight(),
                        contentDescription = "arrow_back",
                        tint = Color.White
                    )
                }
            },
            contentDropDownMenu = {
                DropdownMenuItem(text = { Text(text = "QR/ID") }, onClick = { /*TODO*/ })
                DropdownMenuItem(
                    /**TODO В будущем проверка  не на свой профиль if ()*/
                    text = { Text(text = "Memories") },
                    onClick = { navController.navigate(Route.ListMemoriesScreen.route + "$id") })
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.settings)) },
                    onClick = { /*TODO*/ })
                /**TODO В будущем проверка на свой профиль if ()*/
                Divider()
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.exit)) },
                    onClick = {
                        viewModel.exit()
                        navController.navigate(Route.SigInScreen.route)
                    })
            })
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                modifier = Modifier.height(120.dp),
            ) {
                AsyncImage(
                    model = viewModel.account!!.avatar.toBitmap(), contentDescription = "avatar",
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
            horizontalArrangement = Arrangement.spacedBy(4.dp),
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
            if (listMemories.isNotEmpty()) {
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
                            .fillMaxHeight(0.7f),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        content = {
                            items(listMemories) {
                                ItemsMemory(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .border(4.dp, color = Saffron),
                                    navController = navController, coreMemories = it

                                )
                            }
                        })
                else LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f),
                    horizontalArrangement = Arrangement.spacedBy(4.dp), content = {
                        items(listMemories) {
                            ItemsMemory(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .border(4.dp, color = Saffron),
                                navController = navController,
                                coreMemories = it
                            )
                        }
                    })
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
                {
                    Button(
                        onClick = {
                            navController.navigate(Route.ChangeOrCreateMemoriesScreen.route + ModeOpenScreen.Create.mode)
                        },
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
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "У вас пока нет воспоминаний." +
                                    " Но не переживайте, что-то обязательно произойдет, " +
                                    "и тогда  вам пригодится этa кнопочка =)",
                            color = TextPrimaryColor
                        )
                        Button(
                            onClick = {
                                navController.navigate(Route.ChangeOrCreateMemoriesScreen.route + ModeOpenScreen.Create.mode)
                            },
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
fun ItemsMemory(modifier: Modifier, navController: NavHostController, coreMemories: CoreMemories) {
    Box(modifier = modifier.clickable {
        navController.navigate(Route.InfoMemoriesScreen.route + "${coreMemories.id}")
    }, contentAlignment = Alignment.TopCenter) {

        AsyncImage(
            model = coreMemories.images.toBitmap(),
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
                text = coreMemories.name,
                style = TextStyle(
                    color = Saffron,
                    fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                ), textAlign = TextAlign.Center,
                maxLines = 1
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
