package com.example.datatransferdemo.pageb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.datatransferdemo.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PageB10 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b10)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val treeDao = db.treeDao()
        // 查询用户
        GlobalScope.launch {
            val tree = treeDao.getUserById(1)
            // 使用user对象
            Log.d("TransferTag", "name=${tree?.name}, age=${tree?.age}")
        }
    }
}