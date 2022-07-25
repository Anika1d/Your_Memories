package com.template.ym.composable.screens.initial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.logo.Logo
import com.template.ym.ui.theme.BackgroundIconColor
import com.template.ym.ui.theme.ParadisePink

@Composable
fun AuthScreen(modifier: Modifier, navController: NavHostController) {
    navController.enableOnBackPressed(true)


    /** Values Text field*/
    var textFieldLogin by remember {
        mutableStateOf("Логин")
    }
    var textFieldPassword by remember {
        mutableStateOf("Пароль")
    }




    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Logo(
            modifier = Modifier
                .padding(end = 90.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(
                value = textFieldLogin,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    containerColor = Color.Transparent
                ),
                onValueChange = { textFieldLogin = it.take(25) })
            OutlinedTextField(
                value = textFieldPassword,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    containerColor = Color.Transparent
                ),
                onValueChange = { textFieldPassword = it.take(25) })
        }
        Button(
            onClick = { navController.navigate(Route.ProfileScreen.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = ParadisePink,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10f),
        ) {
            Text(text = stringResource(id = R.string.sig_in))
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun PreviewAuthScreen() {
//    AuthScreen(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BackgroundIconColor)
//            .padding(4.dp),
//        navController = navController
//    )
//}
