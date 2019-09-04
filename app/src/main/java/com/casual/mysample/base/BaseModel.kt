package com.casual.mysample.base


open class BaseModel<T> {
    var data: T? = null
    val errorCode: Int = -1 // 0
    val errorMsg: String? = ""
}