package com.template.ym.composable.screens.initial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.template.ym.R
import com.template.ym.composable.navigation.Route
import com.template.ym.composable.screens.logo.Logo
import com.template.ym.ui.theme.ParadisePink
import com.template.ym.viewmodels.initial.SigInViewModel

@Composable
fun SigInScreen(modifier: Modifier, navController: NavHostController) {
    val viewModel = viewModel(SigInViewModel::class.java)
    if (viewModel.account != null) {
        navController.navigate(Route.ProfileScreen.route + "${viewModel.account!!.id}")
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        navController.enableOnBackPressed(false)
        Logo(Modifier.padding(end = 90.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(
                onClick = {
                    navController.navigate(Route.AuthScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ParadisePink,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10f),
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(text = stringResource(id = R.string.sig_in))
            }
            Button(
                onClick = {
                    navController.navigate(Route.RegisterScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ParadisePink,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10f),
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(text = stringResource(id = R.string.register))
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewSigInScreen() {
//    SigInScreen(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BackgroundIconColor)
//            .padding(4.dp),
//        navController = navController
//    )
//}