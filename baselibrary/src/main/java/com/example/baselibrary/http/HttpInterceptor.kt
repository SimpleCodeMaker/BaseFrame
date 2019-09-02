package com.example.baselibrary.http

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import java.io.UnsupportedEncodingException
import java.net.URLDecoder

val httpLoggingInterceptor by lazy {
    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
        try {
//            val text = URLDecoder.decode(message, "utf-8")
            val text = message
            Log.d("OKHttp-----", text)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            Log.d("OKHttp-----", message)
//            throw e
        }
    }).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}