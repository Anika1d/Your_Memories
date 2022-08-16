package com.template.ym.composable.tools

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.template.ym.R
import com.template.ym.ui.theme.BackgroundIconColor
import com.template.ym.ui.theme.TextPrimaryColor
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


fun stdModifier(h: Float = 1f, w: Float = 1f): Modifier {
    return Modifier
        .fillMaxHeight(h)
        .fillMaxWidth(w)
        .background(BackgroundIconColor)
        .padding(4.dp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StdOutLinedTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    width: Float = 1f,
    title: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
) {
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
        value = value,
        label = {
            Text(title, color = TextPrimaryColor)
        },
        onValueChange = onValueChange,
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

@Throws(IOException::class)
fun getBytes(inputStream: InputStream): ByteArray? {
    val byteBuffer = ByteArrayOutputStream()
    val bufferSize = 1024
    val buffer = ByteArray(bufferSize)
    var len = 0
    while (inputStream.read(buffer).also { len = it } != -1) {
        byteBuffer.write(buffer, 0, len)
    }
    return byteBuffer.toByteArray()
}

fun ByteArray.toBitmap(): Bitmap {
    return BitmapFactory.decodeByteArray(this, 0, this.size)
}