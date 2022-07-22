package com.template.ym.composable.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.template.ym.R
import com.template.ym.composable.screens.logo.Logo
import com.template.ym.ui.theme.BackgroundIconColor
import com.template.ym.ui.theme.ParadisePink

@Composable
fun RegisterScreen(modifier: Modifier) {

    /** Values Text field*/
    val widthTextField = 0.9f
    var textFieldLogin by remember {
        mutableStateOf("Логин")
    }
    var textFieldPassword by remember {
        mutableStateOf("Пароль")
    }
    var textFieldPasswordCopy by remember {
        mutableStateOf("Повторите Пароль")
    }
    var textFieldFirstName by remember {
        mutableStateOf("Имя")
    }
    var textFieldLastName by remember {
        mutableStateOf("Фамилия")
    }
    var textFieldEmail by remember {
        mutableStateOf("Почта")
    }





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
                /**FirstNameField*/
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(widthTextField),
                    value = textFieldFirstName,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        containerColor = Color.Transparent
                    ),
                    onValueChange = { textFieldFirstName = it.take(25) })
                /**LastNameField*/
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(widthTextField),
                    value = textFieldLastName,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        containerColor = Color.Transparent
                    ),
                    onValueChange = { textFieldLastName = it.take(25) })
                /**LoginField*/
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(widthTextField),
                    value = textFieldLogin,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        containerColor = Color.Transparent
                    ),
                    onValueChange = { textFieldLogin = it.take(25) })
                /**EmailField*/
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(widthTextField),
                    value = textFieldPasswordCopy,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        containerColor = Color.Transparent
                    ),
                    onValueChange = { textFieldPasswordCopy = it.take(25) })
                /**PasswordField*/
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(widthTextField),
                    value = textFieldPassword,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        containerColor = Color.Transparent
                    ),
                    onValueChange = { textFieldPassword = it.take(25) })
                /**PasswordCopyField*/
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(widthTextField),
                    value = textFieldEmail,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        containerColor = Color.Transparent
                    ),
                    onValueChange = { textFieldEmail = it.take(25) })
                Button(
                    modifier = Modifier.fillMaxWidth(widthTextField),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ParadisePink,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10f),
                ) {
                    Text(text = stringResource(id = R.string.register))
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(
        Modifier
            .fillMaxSize()
            .background(BackgroundIconColor)
    )
}