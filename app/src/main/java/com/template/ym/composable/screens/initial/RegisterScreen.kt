package com.template.ym.composable.screens.initial

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.logo.Logo
import com.template.ym.composable.tools.StdOutLinedTextField
import com.template.ym.ui.theme.ParadisePink
import com.template.ym.ui.theme.Saffron
import com.template.ym.ui.theme.TextPrimaryColor

@Composable
fun RegisterScreen(
    modifier: Modifier,
    imageUri: Uri?,
    getImageContent: ActivityResultLauncher<String>,
    navController: NavController
) {
    navController.enableOnBackPressed(true)

    /** Values Text field*/
    val widthTextField = 0.9f
//    var textFieldLogin by remember {
//        mutableStateOf("Логин")
//    }
//    var textFieldPassword by remember {
//        mutableStateOf("Пароль")
//    }
//    var textFieldPasswordCopy by remember {
//        mutableStateOf("Повторите Пароль")
//    }
//    var textFieldFirstName by remember {
//        mutableStateOf("Имя")
//    }
//    var textFieldLastName by remember {
//        mutableStateOf("Фамилия")
//    }
//    var textFieldEmail by remember {
//        mutableStateOf("Почта")
//    }
//


    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(1) {
            Logo(
                modifier = Modifier
                    .padding(end = 90.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                /**Avatar */
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(20f))
                        .background(Saffron)
                        .clickable {
                            getImageContent.launch("image/*")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri == null)
                        Text(text = "Avatar")
                    else
                        AsyncImage(model = imageUri, contentDescription = null)
                }
                /**FirstNameField*/
                StdOutLinedTextField(title = "First Name")
                /**LastNameField*/
                StdOutLinedTextField(title = "Last Name")
                /**LoginField*/
                StdOutLinedTextField(title = "Login")
                /**EmailField*/
                StdOutLinedTextField(
                    title = "Email",
                    keyboardType = KeyboardType.Email
                )
                /**PasswordField*/
                StdOutLinedTextField(
                    title = "Password",
                    keyboardType = KeyboardType.Password
                )
                /**PasswordCopyField*/
                StdOutLinedTextField(
                    title = "Repeat Password",
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
                Button(
                    modifier = Modifier.fillMaxWidth(widthTextField),
                    onClick = { navController.navigate(Route.AuthScreen.route) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ParadisePink,
                        contentColor = TextPrimaryColor
                    ),
                    shape = RoundedCornerShape(10f),
                ) {
                    Text(text = stringResource(id = R.string.register))
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun PreviewRegisterScreen() {
//    RegisterScreen(
//        Modifier
//            .fillMaxSize()
//            .background(BackgroundIconColor),
//    )
//}
