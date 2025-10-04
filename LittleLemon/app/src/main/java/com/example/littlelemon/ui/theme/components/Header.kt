package com.example.littlelemon.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R

@Composable
fun Header(){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Little Lemon logo",
        modifier = Modifier
            .width(300.dp)
            .height(150.dp)
    )
}