package com.example.datatransferdemo.pageb

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class Tree(
    @PrimaryKey val id: Int,
    val name: String,
    val age: Int
)

@Dao
interface TreeDao {
    @Insert
    suspend fun insertTree(tree: Tree): Long

    @Query("SELECT * FROM tree WHERE id = :id")
    suspend fun getUserById(id: Int): Tree?
}

@Database(entities = [Tree::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun treeDao(): TreeDao
}