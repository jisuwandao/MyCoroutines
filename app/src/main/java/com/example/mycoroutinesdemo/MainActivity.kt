package com.example.mycoroutinesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.mycoroutinesdemo.acts.MainActivity01
import com.example.mycoroutinesdemo.acts.MainActivity02
import com.example.mycoroutinesdemo.acts.MainActivity03
import com.example.mycoroutinesdemo.acts.MainActivity04
import com.example.mycoroutinesdemo.widgets.FloatingDragView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

//class MainActivity : AppCompatActivity(), CoroutineScope {
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_01).setOnClickListener {
            startActivity(Intent(this, MainActivity01::class.java))
        }

        findViewById<Button>(R.id.btn_02).setOnClickListener {
            startActivity(Intent(this, MainActivity02::class.java))
        }

        findViewById<Button>(R.id.btn_03).setOnClickListener {
            startActivity(Intent(this, MainActivity03::class.java))
        }

        findViewById<Button>(R.id.btn_10).setOnClickListener {
            testCoroutineStart()
        }
    }

    private fun createCoroutine() {

        runBlocking {  }

        val job01 : Job = GlobalScope.launch {  }
        job01.cancel()

        val mainScoroutine = MainScope()

        mainScoroutine.launch {
            async {

            }

            async {

            }
        }
    }

    /**
     * 测试启动模式
     */
    private fun testCoroutineStart(){
        GlobalScope.launch(Dispatchers.Main){
            val job = launch(Dispatchers.IO, start = CoroutineStart.ATOMIC) {
                Log.d("${Thread.currentThread().name}线程", "-> 挂起前")
                delay(1000)
                Log.d("${Thread.currentThread().name}线程", "-> 挂起后")
            }
            Log.d("${Thread.currentThread().name}线程", "-> Dispatchers.Main")
            job.cancel()

//            Log.d("${Thread.currentThread().name}线程", "-> join前")
//            job.join()
            //job.cancel()
//            Log.d("${Thread.currentThread().name}线程", "-> join后")

        }

    }




    private fun testCoroutines() {
        // CoroutineScope(this)

        //runBlocking {  }

        //GlobalScope.launch {  }

        //val corouScope = CoroutineScope(coroutineContext)
        //corouScope.launch {  }

        /*CoroutineScope(coroutineContext).launch {

        }*/

        val mainScoroutine = MainScope()
        //mainScoroutine.launch {  }


        //===========================================================
        println("============================== start =============================")
        fun jobTest() = /*mainScoroutine.launch*/ runBlocking{
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0

                while (i < 5) {//打印前五条消息
                    /*if (System.currentTimeMillis() >= nextPrintTime) {//每秒钟打印两次消息
                        println("job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500
                    }*/

                    delay(500)
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500
                }
            }

//            delay(1200)//延迟1.2s
            println("等待1.2秒后")

//            job.cancel()
            println("协程被取消")
        }
        println("============================== end ================================")
        //===========================================================
    }

//    override val coroutineContext: CoroutineContext
//        // get() = TODO("Not yet implemented")
//        get() = coroutineContext + Job()


}




















