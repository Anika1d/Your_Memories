package com.template.ym.composable.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.template.ym.R
import com.template.ym.ui.theme.BackgroundIconColor
import com.template.ym.ui.theme.PrimaryTextColor
import com.template.ym.ui.theme.TextPrimaryColor


fun stdModifier(): Modifier {
  return  Modifier
        .fillMaxSize()
        .background(BackgroundIconColor)
        .padding(4.dp)
}

@Composable
fun StdOutLinedTextField(
    width: Float = 1f,
    title: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
) {
    var valueOut by remember {
        mutableStateOf("")
    }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var imageVectorMode by remember {
        mutableStateOf(true)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(width)
            .focusRequester(
                focusRequester
            ),
        value = valueOut,
        label = {
            Text(title, color = TextPrimaryColor)
        },
        onValueChange = { valueOut += it[it.length - 1] },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = TextPrimaryColor,
            containerColor = Color.Transparent,
            cursorColor = TextPrimaryColor,
            focusedBorderColor = TextPrimaryColor,
            unfocusedBorderColor = Color.Black

        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        trailingIcon = {
            if (keyboardType == KeyboardType.Password)
                IconToggleButton(
                    checked = imageVectorMode,
                    onCheckedChange = { imageVectorMode = !imageVectorMode },
                    colors = IconButtonDefaults.iconToggleButtonColors(
                        contentColor = TextPrimaryColor
                    )
                ) {
                    Icon(
                        painter = painterResource(
                            id =
                            if (imageVectorMode) R.drawable.ic_baseline_visibility_24
                            else R.drawable.ic_baseline_visibility_off_24
                        ),
                        tint = TextPrimaryColor,
                        contentDescription = null
                    )
                }
        },
        visualTransformation = if (imageVectorMode) VisualTransformation.None else
            PasswordVisualTransformation()
    )
}