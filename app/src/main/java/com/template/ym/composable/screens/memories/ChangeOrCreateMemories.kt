package com.template.ym.composable.screens.memories

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.template.ym.R
import com.template.ym.composable.screens.bar.BottomBar
import com.template.ym.composable.screens.bar.Toolbar
import com.template.ym.composable.tools.StdOutLinedTextField
import com.template.ym.ui.theme.ParadisePink
import com.template.ym.ui.theme.Saffron
import com.template.ym.ui.theme.TextPrimaryColor

@Composable
fun ChangeOrCreateMemories(
    modifier: Modifier,
    imageUri: Uri?,
    getImageContent: ActivityResultLauncher<String>,
    navController: NavHostController,
    modeScreen: String
) {

    Column {
        Toolbar(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            title = if (modeScreen=="create") "Создание" else "Редактирование",
            navController = navController,
            back_flag = true
        ) {
            DropdownMenuItem(text = { Text("Отмена") }, onClick = { navController.navigateUp() })
        }
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {


            /**Avatar */
            Box(
                modifier = Modifier
                    .width(220.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(20f))
                    .background(Saffron)
                    .clickable {
                        getImageContent.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri == null)
                    Text(text = "Photo Memories")
                else
                    AsyncImage(model = imageUri, contentDescription = null)
            }
            /**NameField*/
            StdOutLinedTextField(title = "Name Memories")

            /**DescriptionField*/
            StdOutLinedTextField(title = "Description Memories")
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ParadisePink,
                    contentColor = TextPrimaryColor
                ),
                shape = RoundedCornerShape(10f),
                modifier = Modifier.fillMaxWidth(0.6f),
                content = {
                    Text(text = "Отметить друзей")
                })
            Box(
                modifier = Modifier.fillMaxSize(1f),
                contentAlignment = Alignment.BottomCenter
            )
            {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ParadisePink,
                        contentColor = TextPrimaryColor
                    ),
                    shape = RoundedCornerShape(10f),
                    modifier = Modifier.fillMaxWidth(0.6f),
                    content = {
                        Text(text = stringResource(id = R.string.create_memories))
                    }
                )
            }
        }
        BottomBar(
            Modifier
                .fillMaxWidth(1f)
                .height(50.dp)
                .background(Color.Black)
        )
    }
}
