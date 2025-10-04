package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(navController: NavHostController?) {
    Column( Modifier.fillMaxSize().padding(vertical = 35.dp, horizontal = 15.dp) ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon logo",
                modifier = Modifier
                    .width(250.dp)
                    .height(58.dp)
            )
            Image(
                modifier = Modifier.size(65.dp).clickable(onClick = { navController!!.navigate(ProfileScreen.route)}),
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile logo",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    LittleLemonTheme {
        Home(navController = null)
    }
}