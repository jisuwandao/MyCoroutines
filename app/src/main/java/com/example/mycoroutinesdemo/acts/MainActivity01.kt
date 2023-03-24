package com.example.mycoroutinesdemo.acts

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.mycoroutinesdemo.R
import com.example.mycoroutinesdemo.api.APIClient
import com.example.mycoroutinesdemo.api.WanAndroidAPI
import com.example.mycoroutinesdemo.bean.LoginRegisterResponse2
import com.example.mycoroutinesdemo.bean.LoginRegisterResponseWrapper2

/**
 * 传统方式完成异步任务网络加载
 */
class MainActivity01 : AppCompatActivity() {
    private lateinit var tvContent: TextView
    private lateinit var btn: Button


    // TODO 第二步骤：主线程 更新UI（本次所有代码用Kotlin写，如果是Java代码量更多）
    val mHandler = Handler(Looper.getMainLooper()) {
        // as xx 转换成xx类型

        val result =  it?.obj as LoginRegisterResponseWrapper2<LoginRegisterResponse2>
        Log.d(TAG, "errorMsg: ${result?.data}")
        tvContent.text = result?.data.toString() // 更新控件 UI

        mProgressDialog?.dismiss() // 隐藏加载框

        false
    }

    private val TAG = "MainActivity01"
    private var mProgressDialog: ProgressDialog? = null

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

        // TODO 第一步骤：异步线程开启 请求服务器
        object: Thread() {
            override fun run() {
                super.run()

                sleep(2000)

                val loginResult =
                    APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                        .loginAction("myCoroutine", "123456")

                val result : LoginRegisterResponseWrapper2<LoginRegisterResponse2>? = loginResult.execute().body()

                // 切换android 主线程，更新UI  把最终登录成功的JavaBean（KT数据类） 发送给  Handler
                val msg = mHandler.obtainMessage()
                msg.obj = result
                mHandler.sendMessage(msg)
            }
        }.start()
    }

}