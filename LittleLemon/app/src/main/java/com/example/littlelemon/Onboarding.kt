package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.components.AppTextField
import com.example.littlelemon.ui.theme.components.Header

@Composable
fun Onboarding(navController: NavHostController?, context: Context) {

    var firstName by remember { mutableStateOf(TextFieldValue("")) }

    var lastName by remember { mutableStateOf(TextFieldValue("")) }

    var email by remember { mutableStateOf(TextFieldValue("")) }


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
        Column(
            Modifier.fillMaxSize(.95f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                "Personal information",
                style = MaterialTheme.typography.bodyLarge
            )
            AppTextField(
                label = "First name",
                value = firstName,
                onValueChange = { firstName = it })
            AppTextField(label = "Last name", value = lastName, onValueChange = { lastName = it })
            AppTextField(label = "Email address", value = email, onValueChange = { email = it })
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    if (firstName.text.isBlank() || lastName.text.isBlank() || email.text.isBlank()) {
                        Toast.makeText(
                            context,
                            "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        with(sharedPreferences.edit()){
                            putString("firstName", firstName.text)
                            putString("lastName", lastName.text)
                            putString("email", email.text)
                            putBoolean("isUserOnboarding", false)
                            apply()
                        }
                        Toast.makeText(
                            context,
                            "Registration successful!",
                            Toast.LENGTH_LONG
                        ).show()
                        navController!!.navigate(HomeScreen.route)

                    }
                }
            ) {
                Text(
                    "Register",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewOnBoarding() {
    LittleLemonTheme {
        Onboarding(
            navController = null,
            context = LocalContext.current
        )
    }
}