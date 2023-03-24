package com.example.mycoroutinesdemo.acts

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.mycoroutinesdemo.R
import com.example.mycoroutinesdemo.api.APIClient
import com.example.mycoroutinesdemo.api.WanAndroidAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * 传统方式完成异步任务网络加载
 */
class MainActivity02 : AppCompatActivity() {
    private lateinit var tvContent: TextView
    private lateinit var btn: Button

    private val TAG = "MainActivity02"
    private var mProgressDialog: ProgressDialog? = null

    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main01)

        tvContent = findViewById(R.id.tv_title)
        btn= findViewById(R.id.btn_01)
        btn.setOnClickListener { startRequest() }
    }

    // 按钮 点击事件
    private fun startRequest() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog?.setTitle("请求服务器中...")
        mProgressDialog?.show()

        mainScope.launch(Dispatchers.Main) {
            val result = APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                .loginActionCoroutine("myCoroutine", "123456")

            // 更新UI
            Log.d(TAG, "errorMsg=${result.data}")
            tvContent.text = result?.data?.toString() // 更新控件

            mProgressDialog?.dismiss()
        }
    }

    override fun onDestroy() {
        mainScope?.cancel()
        super.onDestroy()
    }

}