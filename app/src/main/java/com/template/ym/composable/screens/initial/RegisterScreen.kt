package com.template.ym.composable.screens.initial

import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.template.core.model.account.EnterAccount
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.logo.Logo
import com.template.ym.composable.tools.StdOutLinedTextField
import com.template.ym.composable.tools.getBytes
import com.template.ym.ui.theme.ParadisePink
import com.template.ym.ui.theme.Saffron
import com.template.ym.ui.theme.TextPrimaryColor
import com.template.ym.viewmodels.initial.RegisterViewModel
import java.io.InputStream


@Composable
fun RegisterScreen(
    modifier: Modifier,
    imageUri: Uri?,
    getImageContent: ActivityResultLauncher<String>,
    navController: NavController
) {
    navController.enableOnBackPressed(true)
    val viewModel = viewModel(RegisterViewModel::class.java)

    /** Values Text field*/
    val widthTextField = 0.9f
    var firstNameField by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var lastNameField by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var emailField by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }


    var passwordField by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }


    var passwordCopyField by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    val context = LocalContext.current

    var imageByteArray: ByteArray? = null
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
                    else {
                        AsyncImage(model = imageUri, contentDescription = null)
                        val iStream: InputStream =
                            context.contentResolver.openInputStream(imageUri)!!
                        imageByteArray = getBytes(iStream)!!

                    }
                }
                /**FirstNameField*/
                StdOutLinedTextField(
                    value = firstNameField,
                    onValueChange = { firstNameField = it },
                    title = "First Name"
                )
                /**LastNameField*/
                StdOutLinedTextField(
                    value = lastNameField,
                    onValueChange = { lastNameField = it }, title = "Last Name"
                )
//                /**LoginField*/
//                StdOutLinedTextField(title = "Login")
                /**EmailField*/
                StdOutLinedTextField(
                    value = emailField,
                    onValueChange = { emailField = it },
                    title = "Email",
                    keyboardType = KeyboardType.Email,
                )
                /**PasswordField*/
                StdOutLinedTextField(
                    value = passwordField,
                    onValueChange = { passwordField = it },
                    title = "Password",
                    keyboardType = KeyboardType.Password,
                )
                /**PasswordCopyField*/
                StdOutLinedTextField(
                    value = passwordCopyField,
                    onValueChange = { passwordCopyField = it },
                    title = "Repeat Password",
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                )
                Button(
                    modifier = Modifier.fillMaxWidth(widthTextField),
                    onClick = {
                        if (imageByteArray != null && passwordField.text != "" &&
                            passwordCopyField.text != "" && emailField.text != "" &&
                            firstNameField.text != "" && lastNameField.text != ""
                        )
                            if (passwordField.text == passwordCopyField.text) {
                                viewModel.createAccount(
                                    EnterAccount(
                                        firstName = firstNameField.text,
                                        lastName = lastNameField.text,
                                        email = emailField.text,
                                        password = passwordField.text,
                                        avatar = imageByteArray!!
                                    )
                                )
                                Toast.makeText(context, "Успешно", Toast.LENGTH_LONG).show()
                                navController.navigate(Route.AuthScreen.route)
                            } else {
                                Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_LONG)
                                    .show()
                            }
                        else {
                            Toast.makeText(context, "Не все поля заполнены", Toast.LENGTH_LONG)
                                .show()
                        }
                    },
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
