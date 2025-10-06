package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class RoomEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Dao
interface RoomEntityDao {
    @Query("SELECT * FROM RoomEntity")
    fun getAll(): LiveData<List<RoomEntity>>

    @Insert
    fun insertAll(vararg items: RoomEntity)

    @Query("SELECT (SELECT COUNT(*) FROM RoomEntity) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [RoomEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun roomEntityDao(): RoomEntityDao
}