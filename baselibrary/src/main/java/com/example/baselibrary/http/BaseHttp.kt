package com.example.baselibrary.http

import com.example.baselibrary.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.TimeUnit

object BaseHttp {
    const val BASE_URL = "https://wanandroid.com"
    //    const val BASE_URL = "http://www.kuaidi100.com"
    const val DEFAULT_TIME_OUT = 10L

    val okhttpBuilder by lazy {
        OkHttpClient.Builder().apply {
            connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//连接 超时时间
            writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//写操作 超时时间
            readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//读操作 超时时间
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
//            retryOnConnectionFailure(true)//错误重连
        }
    }

    val retrofit by lazy {
        Retrofit.Builder().apply {
            client(okhttpBuilder.build())
            baseUrl(BASE_URL)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(GsonConverterFactory.create())

        }
    }

    inline fun <reified T> getService(): T {
        return retrofit.build().create(T::class.java)
    }
}
