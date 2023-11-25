package com.example.datatransferdemo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.datatransferdemo.databinding.ActivityMainBinding
import com.example.datatransferdemo.pageb.AppDatabase
import com.example.datatransferdemo.pageb.FileHelper
import com.example.datatransferdemo.pageb.PageB1
import com.example.datatransferdemo.pageb.PageB10
import com.example.datatransferdemo.pageb.PageB11
import com.example.datatransferdemo.pageb.PageB12
import com.example.datatransferdemo.pageb.PageB13
import com.example.datatransferdemo.pageb.PageB2
import com.example.datatransferdemo.pageb.PageB3
import com.example.datatransferdemo.pageb.PageB4
import com.example.datatransferdemo.pageb.PageB5
import com.example.datatransferdemo.pageb.PageB6
import com.example.datatransferdemo.pageb.PageB7
import com.example.datatransferdemo.pageb.PageB8
import com.example.datatransferdemo.pageb.PageB9
import com.example.datatransferdemo.pageb.Tree
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.Serializable


var Intent.tag: String?
    get() = getStringExtra("tag")
    set(value) {
        putExtra("tag", value)
    }

// 3.静态变量
object StaticDataHolder {
    var sharedData = mapOf<String,String>()
}

// 5.接口回调（Interface Callback）
interface DataCallback {
    fun onDataReceived(data: String)
}

// 6.EventBus
class MessageEvent(val message: String)

// 7.Application 类
class MyApp : Application() {
    var sharedData: String = ""
}

// 8.Parcelable
@Parcelize
data class User(val name: String, val age: Int) : Parcelable

// 9.Serializable
data class Dog(val name: String, val age: Int) : Serializable

class MainActivity : AppCompatActivity(), DataCallback {

    private lateinit var binding: ActivityMainBinding

    // 静态的DataCallback实例
    companion object {
        var callbackInstance: DataCallback? = null
    }

    override fun onDestroy() {
        super.onDestroy()
        callbackInstance = null // 防止内存泄漏
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private lateinit var fileHelper: FileHelper

    // 获取返回结果，在Activity或Fragment中定义
    private val someActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // 执行成功后的操作
            val intent: Intent? = result.data
            when (intent?.tag) {
                "PageB1" -> {
                    var resultValue = intent?.getStringExtra("result_key")
                    Log.d("TransferTag", "result value=$resultValue")
                }
                "PageB2" -> {
                    val bundle = intent?.extras
                    val receivedID = bundle?.getInt("id")
                    val receivedStatus = bundle?.getBoolean("status")
                    val receivedContent = bundle?.getString("content")
                    Log.d("TransferTag", "result value receivedContent=$receivedContent, receivedID=$receivedID, receivedStatus=$receivedStatus")
                }
                "PageB3" -> {

                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dealwithOnClick()
    }

    private fun dealwithOnClick() {
        // 1.Intent
        binding.button1.setOnClickListener {
            val intent = Intent(this, PageB1::class.java)
            intent.putExtra("key", "传递字符串") // 可选：添加要传递的数据
            // 启动目标 Activity
            //startActivity(intent)
            // 如果希望在目标 Activity 中获取返回结果，使用ActivityResultLauncher来启动
            someActivityResultLauncher.launch(intent);
        }

        // 2.Bundle
        binding.button2.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id",123)
            bundle.putBoolean("status",true)
            bundle.putString("content", "传递字符串")

            val intent = Intent(this, PageB2::class.java)
            intent.putExtras(bundle)
            // 启动目标 Activity
            //startActivity(intent)
            // 获取返回结果启动
            someActivityResultLauncher.launch(intent);
        }

        // 3.静态变量
        binding.button3.setOnClickListener {
            StaticDataHolder.sharedData = mapOf<String,String>("id" to "1234", "status" to "1", "content" to "传递字符串")

            val intent = Intent(this, PageB3::class.java)
            startActivity(intent)
        }

        // 4.SharedPreferences
        binding.button4.setOnClickListener {
            val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("key", "传递字符串") // data 是要传递的数据
            editor.apply()

            val intent = Intent(this, PageB4::class.java)
            startActivity(intent)
        }

        // 5.接口回调（Interface Callback）
        binding.button5.setOnClickListener {
            callbackInstance = this;
            // 在需要传递数据的地方将实现类的实例传递给页面 B
            val intent = Intent(this, PageB5::class.java)
            startActivity(intent)
        }

        // 6.EventBus
        binding.button6.setOnClickListener {
            // EventBus.getDefault().register(this)

            val intent = Intent(this, PageB6::class.java)
            startActivity(intent)
        }

        // 7.Application 类
        binding.button7.setOnClickListener {
            val myApp = application as MyApp
            myApp.sharedData = "传递字符串" // data 是要传递的数据

            val intent = Intent(this, PageB7::class.java)
            startActivity(intent)
        }

        // 8.Parcelable
        binding.button8.setOnClickListener {
            val intent = Intent(this, PageB8::class.java).apply {
                val user = User("John Doe", 30)
                putExtra("USER_KEY", user)
            }
            startActivity(intent)
        }

        // 9.Serializable
        binding.button9.setOnClickListener {
            val intent = Intent(this, PageB9::class.java).apply {
                val dog = Dog("John Doe", 30)
                putExtra("USER_KEY", dog)
            }
            startActivity(intent)
        }

        // 10.数据库（Database）
        binding.button10.setOnClickListener {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()
            val treeDao = db.treeDao()
            // 插入用户
            GlobalScope.launch {
                treeDao.insertTree(Tree(6, "John Doe", 30)) // 测试时每次修改一下ID，不然存在相同ID会报错
            }

            val intent = Intent(this, PageB10::class.java)
            startActivity(intent)
        }

        // 11.文件（File）
        binding.button11.setOnClickListener {
            fileHelper = FileHelper(this)
            // 保存数据到文件
            val dataToSave = "Some data to be shared"
            fileHelper.writeToFile("shared_data.txt", dataToSave)

            val intent = Intent(this, PageB11::class.java)
            startActivity(intent)
        }

        // 12. 网络请求（Network Request）
        binding.button12.setOnClickListener {
            val intent = Intent(this, PageB12::class.java)
            startActivity(intent)
        }

        // 13.ContentProvider
        binding.button13.setOnClickListener {
            val intent = Intent(this, PageB13::class.java)
            startActivity(intent)
        }

    }

    override fun onDataReceived(data: String) {
        // 处理接收到的数据
        Log.d("TransferTag", data)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onMessageEvent(event: MessageEvent) {
        // 处理接收到的数据
        val data = event.message
        // ...
        Log.d("TransferTag", data)
    }

}


