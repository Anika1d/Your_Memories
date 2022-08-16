package com.template.ym.composable.screens.memories

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.template.core.model.memories.CoreMemories
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.bar.BottomBar
import com.template.ym.composable.screens.bar.Toolbar
import com.template.ym.composable.screens.memories.modeopenscreen.ModeOpenScreen
import com.template.ym.ui.theme.ParadisePink
import com.template.ym.ui.theme.RoyalPurple
import com.template.ym.ui.theme.TextPrimaryColor
import com.template.ym.viewmodels.memories.ListMemoriesViewModel


@Composable
fun ListMemories(
    modifier: Modifier, navController: NavHostController, idOwner: Long
) {
    val viewModel = viewModel(ListMemoriesViewModel::class.java)
    var listMemories: List<CoreMemories>
    viewModel.apply {
        initMemories(
            idOwner
        )
        listMemories = viewModel.listMemories
    }
    Column() {
        Toolbar(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            title = "Воспоминания",

            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        modifier = Modifier.fillMaxHeight(),
                        contentDescription = "arrow_back",
                        tint = Color.White
                    )
                }
            }, contentDropDownMenu = {
                DropdownMenuItem(text = { Text(text = "Создать") }, onClick = {
                    navController.navigate(Route.ChangeOrCreateMemoriesScreen.route + ModeOpenScreen.Create.mode)
                })
            }
        )
        if (listMemories.isNotEmpty())
            LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listMemories) {
                    ItemListMemory(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(RoyalPurple),
                        navController = navController,
                        coreMemories = it
                    )
                }
            }
        else {
            Box(modifier = modifier, contentAlignment = Alignment.Center) {
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
        BottomBar(
            Modifier
                .fillMaxWidth(1f)
                .height(40.dp)
                .background(Color.Black)
        )
    }
}

@Composable
fun ItemListMemory(
    modifier: Modifier,
    navController: NavHostController,
    coreMemories: CoreMemories
) {
    Column(
        modifier = modifier.clickable {
            navController.navigate(Route.InfoMemoriesScreen.route + "${coreMemories.id}")
        },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(Modifier.fillMaxSize()) {
            Text(text = coreMemories.name, color = TextPrimaryColor)
        }
        Row(Modifier.fillMaxSize(), Arrangement.SpaceBetween) {
            Text(
                text = "MOI PARNI" //TODO Frieds
                , color = TextPrimaryColor
            )
            Text(text = coreMemories.description.take(20), color = TextPrimaryColor)
        }
    }
}
