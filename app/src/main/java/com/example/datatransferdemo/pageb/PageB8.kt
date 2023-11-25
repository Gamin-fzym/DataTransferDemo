package com.example.datatransferdemo.pageb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.datatransferdemo.R
import com.example.datatransferdemo.User

class PageB8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b8)

        val user = intent.getParcelableExtra<User>("USER_KEY")
        user?.let {
            // 使用user对象
            Log.d("TransferTag", "name=${user.name}, age=${user.age}")
        }
    }
}