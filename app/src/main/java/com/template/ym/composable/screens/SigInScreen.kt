package com.template.ym.composable.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun SigInScreen(modifier: Modifier) {
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

            Button(
                onClick = { /*TODO*/ },
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
                onClick = { /*TODO*/ },
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


@Preview(showBackground = true)
@Composable
fun PreviewSigInScreen() {
    SigInScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundIconColor)
            .padding(4.dp)
    )
}