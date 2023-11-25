package com.example.datatransferdemo.pageb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datatransferdemo.MessageEvent
import com.example.datatransferdemo.R
import org.greenrobot.eventbus.EventBus

class PageB6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b6)

        EventBus.getDefault().post(MessageEvent("Hello from SenderActivity!"))
    }
}