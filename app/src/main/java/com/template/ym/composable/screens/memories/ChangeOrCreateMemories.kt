package com.template.ym.composable.screens.memories

import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.template.core.model.memories.CoreMemories
import com.template.core.model.memories.EnterMemories
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.bar.BottomBar
import com.template.ym.composable.screens.bar.Toolbar
import com.template.ym.composable.tools.StdOutLinedTextField
import com.template.ym.composable.tools.getBytes
import com.template.ym.composable.tools.toBitmap
import com.template.ym.ui.theme.ParadisePink
import com.template.ym.ui.theme.Saffron
import com.template.ym.ui.theme.TextPrimaryColor
import com.template.ym.viewmodels.memories.ChangeOrCreateMemoriesViewModel
import kotlinx.coroutines.runBlocking
import java.io.InputStream

@Composable
fun ChangeOrCreateMemories(
    modifier: Modifier,
    imageUri: Uri?,
    getImageContent: ActivityResultLauncher<String>,
    navController: NavHostController,
    modeScreen: String,
    idMemories: Long?,
) {
    val viewModel = viewModel(ChangeOrCreateMemoriesViewModel::class.java)
    val idOwnerMemories = viewModel.account!!.id
    val memories by
    remember {
        mutableStateOf(
            if (modeScreen == "create") null else {
                runBlocking {
                    viewModel.initMemories(id = idMemories!!.toLong())
                    viewModel.memories
                }
            }
        )
    }
    val context = LocalContext.current
    var nameField by remember {
        mutableStateOf(
            TextFieldValue(
                text = memories?.name ?: ""
            )
        )
    }
    var descriptionField by remember {
        mutableStateOf(TextFieldValue(text = memories?.description ?: ""))
    }
    var imageByteArray: ByteArray? = null
    Column {
        Toolbar(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            title = if (modeScreen == "create") "Создание" else "Редактирование",
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        modifier = Modifier.fillMaxHeight(),
                        contentDescription = "arrow_back",
                        tint = Color.White
                    )
                }
            },
            contentDropDownMenu = {
                DropdownMenuItem(
                    text = { Text("Отмена") },
                    onClick = { navController.navigateUp() })
            },
        )
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
                if (memories == null) {
                    if (imageUri != null) {
                        val iStream: InputStream =
                            context.contentResolver.openInputStream(imageUri)!!
                        imageByteArray = getBytes(iStream)!!
                        AsyncImage(model = imageByteArray!!.toBitmap(), contentDescription = null)
                    } else {
                        Text(text = "Photo Memories")
                    }
                } else {
                    if (imageUri == null) {
                        imageByteArray = memories!!.images
                        AsyncImage(model = imageByteArray!!.toBitmap(), contentDescription = null)
                    } else {
                        val iStream: InputStream =
                            context.contentResolver.openInputStream(imageUri)!!
                        imageByteArray = getBytes(iStream)!!
                        AsyncImage(model = imageByteArray!!.toBitmap(), contentDescription = null)
                    }
                }
//                if (imageUri == null)
//                    Text(text = "Photo Memories")
//                else {
//                    AsyncImage(model = imageUri, contentDescription = null)
//                    val iStream: InputStream =
//                        context.contentResolver.openInputStream(imageUri)!!
//                    imageByteArray = getBytes(iStream)!!
//                }
            }
            /**NameField*/
            StdOutLinedTextField(
                value = nameField, onValueChange = { nameField = it }, title = "Name Memories"
            )

            /**DescriptionField*/
            StdOutLinedTextField(
                value = descriptionField,
                onValueChange = { descriptionField = it },
                title = "Description Memories",
                imeAction = ImeAction.Done
            )
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
                    onClick = {
                        if (imageByteArray != null &&
                            descriptionField.text != ""
                            && nameField.text != ""
                        ) {
                            Toast.makeText(context, "Успешно", Toast.LENGTH_LONG).show()
                            if (modeScreen == "create") {
                                viewModel.createMemories(
                                    EnterMemories(
                                        idOwner = idOwnerMemories,
                                        name = nameField.text,
                                        description = descriptionField.text,
                                        images = imageByteArray!!
                                    )
                                )

                            } else {
                                viewModel.updateMemories(
                                    coreMemories = CoreMemories(
                                        id = memories!!.id,
                                        idOwner = memories!!.idOwner,
                                        name = nameField.text,
                                        description = descriptionField.text,
                                        images = imageByteArray!!
                                    )
                                )

                            }
                            navController.navigate(
                                Route.ProfileScreen.route +
                                        "$idOwnerMemories"
                            )
                        } else {
                            Toast.makeText(context, "Не все поля заполнены", Toast.LENGTH_LONG)
                                .show()
                        }
                    },
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
