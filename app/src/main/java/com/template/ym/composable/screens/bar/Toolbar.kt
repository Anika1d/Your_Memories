package com.template.ym.composable.screens.bar

import android.annotation.SuppressLint
import android.content.res.Resources
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Toolbar(
    modifier: Modifier,
    title: String,
    navController: NavHostController,
    back_flag: Boolean,
    contentDropDownMenu: @Composable() (ColumnScope.() -> Unit),
) {
    var backPressed by remember {
        mutableStateOf(0L)
    }
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val weightScreen = Resources.getSystem().displayMetrics.widthPixels
    Scaffold(
        modifier = modifier,
        containerColor = Color.Black,
        topBar = {
            SmallTopAppBar(
                modifier = modifier,
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.8f),
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(
                                textAlign = TextAlign.Center,
                                text = title,
                                color = Color.White
                            )
                        })
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (back_flag ||
                            backPressed + 2000 > System.currentTimeMillis()
                        ) navController.navigateUp()
                        else {
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
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Default.Menu,
                            contentDescription = "menu"
                        )
                    }

                },
            )
        },
    ) {
        DropdownMenu(
            offset = DpOffset(weightScreen.dp, 0.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            content = contentDropDownMenu,
        )
    }
}