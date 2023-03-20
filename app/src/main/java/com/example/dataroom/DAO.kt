package com.example.dataroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Insert
    fun insertItem(item: Item)

    @Query("SELECT * FROM Items")
    fun getAllItem(): Flow<List<Item>>
}