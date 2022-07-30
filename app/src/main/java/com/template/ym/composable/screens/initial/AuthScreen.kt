package com.template.ym.composable.screens.initial

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.logo.Logo
import com.template.ym.composable.tools.StdOutLinedTextField
import com.template.ym.ui.theme.ParadisePink
import com.template.ym.ui.theme.TextPrimaryColor
import com.template.ym.viewmodels.initial.AuthViewModel
import com.template.ym.viewmodels.initial.RegisterViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun AuthScreen(modifier: Modifier, navController: NavHostController) {
    navController.enableOnBackPressed(true)
    val viewModel = viewModel(AuthViewModel::class.java)
    var emailField by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var passwordField by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    val context = LocalContext.current
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
                value = emailField,
                onValueChange = { emailField = it },
                title = "Email",
                keyboardType = KeyboardType.Email
            )
            StdOutLinedTextField(
                value = passwordField,
                onValueChange = { passwordField = it },
                title = "Password",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        }
        Button(
            onClick = {
                runBlocking { viewModel.auth(emailField.text, password = passwordField.text) }
                if (viewModel.account != null)
                    navController.navigate(Route.ProfileScreen.route + "${viewModel.account!!.id}")
                else
                    Toast.makeText(context, "Неверная почта или пароль", Toast.LENGTH_LONG).show()
            },
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
