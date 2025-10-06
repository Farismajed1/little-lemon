package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android){
        install(ContentNegotiation) {
            json(contentType = ContentType(contentType = "text", contentSubtype = "plain"))
        }
    }


    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "Database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {

                val menuItems = database.roomEntityDao().getAll().observeAsState(emptyList())

                Surface {
                    val navController = rememberNavController()
                    Navigation(navController, this, dishes = menuItems.value)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO){
            if(database.roomEntityDao().isEmpty()){
                val menuItemNetwork = fetchData()
                saveMenuToDatabase(menuItemNetwork)
            }
        }

    }

    private suspend fun fetchData(): List<MenuItemNetwork> {
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetworkData>().menu
    }

    private fun saveMenuToDatabase(menuItem: List<MenuItemNetwork>) {
        val menuItemToRoom = menuItem.map{ it.toRoomEntity() }
        database.roomEntityDao().insertAll(*menuItemToRoom.toTypedArray())
    }
}