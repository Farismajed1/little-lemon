package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(navController: NavHostController, dishes: List<RoomEntity>) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 25.dp, top = 30.dp, bottom = 15.dp),
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
                modifier = Modifier
                    .size(65.dp)
                    .clickable(onClick = { navController!!.navigate(ProfileScreen.route) }),
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile logo",
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.45f)
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = "Little Lemon",
                modifier = Modifier.padding(start = 25.dp, top = 15.dp),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Row(Modifier.padding(horizontal = 25.dp)) {
                Column(Modifier.fillMaxWidth(.65f)) {
                    Text(
                        text = "Chicago",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "We are a family owned\n" +
                                "Mediterranean restaurant,\n" +
                                "focused on traditional\n" +
                                "recipes served with a \n" +
                                "modern twist. ",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFFFFFFF)
                    )
                }
                Image(
                    modifier = Modifier
                        .fillMaxHeight(.6f)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium),
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Image"
                )
            }
            var searchPhrase by remember { mutableStateOf(TextFieldValue("")) }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 20.dp, start = 25.dp, end = 25.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon"
                    )
                },
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                singleLine = true,
                label = { Text("Enter search phrase") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFFFFFF),
                    unfocusedContainerColor = Color(0xFFFFFFFF)
                )
            )
        }
        Column(Modifier.padding(vertical = 15.dp)) {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "ORDER FOR DELIVERY!",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(Modifier.height(8.dp))
            Sections()
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 3.dp),
                color = Color(0xFF000000)
            )
            LazyColumn {
                items(dishes) { dish ->
                    DishItem(
                        title = dish.title,
                        description = dish.description,
                        price = dish.price,
                        imageUrl = dish.image
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DishItem(title: String, description: String, price: String, imageUrl: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .fillMaxWidth(.75f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(title)
            Text(
                description.take(69) + "...",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                price,
                style = MaterialTheme.typography.displaySmall,
            )
        }
        GlideImage(
            model = imageUrl,
            contentDescription = title
        )
    }
}

@Composable
fun Sections() {
    val Selectedsection = remember { mutableStateOf("ggg") }
    val sectionList = listOf("Starters", "Mains", "Dessert", "Sides")
    LazyRow {
        items(sectionList) { section ->
            SectionButton(
                title = section,
                isSelected = section == Selectedsection.value,
                onClick = { Selectedsection.value = section }
            )
            Spacer(Modifier.padding(end = 8.dp))
        }
    }
}


@Composable
fun SectionButton(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(start = 5.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected == false) {
                Color(0xFFC4C4C4)
            } else {
                MaterialTheme.colorScheme.primary
            }
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            title,
            color = Color(0xFF000000)
        )
    }
}