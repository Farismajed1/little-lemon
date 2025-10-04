package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.components.AppTextField

@Composable
fun Profile(navController: NavHostController?, context: Context){

    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("firstName", "null")
    val lastName = sharedPreferences.getString("lastName", "null")
    val email = sharedPreferences.getString("email", "null")

    val firstNameField = TextFieldValue("$firstName")
    val lastNameField = TextFieldValue("$lastName")
    val emailField = TextFieldValue("$email")

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon logo",
            modifier = Modifier
                .width(300.dp)
                .height(150.dp)
        )
        Column(
            Modifier.fillMaxSize(.95f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                "Personal information",
                style = MaterialTheme.typography.bodyLarge
            )
            AppTextField(label = "First name", value = firstNameField, onValueChange = {  }, readOnly = true)
            AppTextField(label = "Last name", value = lastNameField, onValueChange = { }, readOnly = true)
            AppTextField(label = "Email address", value = emailField, onValueChange = { }, readOnly = true)
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    with(sharedPreferences.edit()){
                        putString("firstName", "")
                        putString("lastName", "")
                        putString("email", "")
                        putBoolean("isUserOnboarding", true)
                        apply()
                    }
                    navController!!.navigate(OnboardingScreen.route)
                    sharedPreferences.getBoolean("isUserOnboarding", true)
                }
            ) {
                Text(
                    "Log out",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile(){
    LittleLemonTheme {
        Profile(
            navController = null,
            LocalContext.current
        )
    }
}