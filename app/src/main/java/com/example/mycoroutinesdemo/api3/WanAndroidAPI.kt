package com.example.mycoroutinesdemo.api3

import com.example.mycoroutinesdemo.bean.LoginRegisterResponse2
import com.example.mycoroutinesdemo.bean.LoginRegisterResponseWrapper2
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

// 客户端API 可以访问 服务器的API
interface WanAndroidAPI {

    // TODO >>>>>>>只有协程了 下面是协程API

    /** https://www.wanandroid.com/blog/show/2
     * 登录API
     * username=myCoroutine&password=123456
     */
    @POST("/user/login")
    @FormUrlEncoded
    suspend fun loginActionCoroutine(@Field("username") username: String,
                    @Field("password") password: String)
            : LoginRegisterResponseWrapper2<LoginRegisterResponse2> // 返回值

    /** https://www.wanandroid.com/blog/show/2
     * 注册的API
     */
    @POST("/user/register")
    @FormUrlEncoded
    suspend fun registerActionCoroutine(@Field("username") username: String,
                       @Field("password") password: String,
                       @Field("repassword") repassword: String)
            : LoginRegisterResponseWrapper2<LoginRegisterResponse2> // 返回值
}