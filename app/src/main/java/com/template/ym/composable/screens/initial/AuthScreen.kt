package com.template.ym.composable.screens.initial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.template.ym.R
import com.template.ym.composable.screens.logo.Logo
import com.template.ym.composable.tools.StdOutLinedTextField
import com.template.ym.ui.theme.ParadisePink
import com.template.ym.ui.theme.TextPrimaryColor

@Composable
fun AuthScreen(modifier: Modifier, navController: NavHostController) {
    navController.enableOnBackPressed(true)


    /** Values Text field*/
//    var textFieldEmail by remember {
//        mutableStateOf("")
//    }
//    var textFieldPassword by remember {
//        mutableStateOf("")
//    }



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
            StdOutLinedTextField(
                title = "Email",
                keyboardType = KeyboardType.Email
            )
            StdOutLinedTextField(
                title = "Password",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = ParadisePink,
                contentColor = TextPrimaryColor
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
