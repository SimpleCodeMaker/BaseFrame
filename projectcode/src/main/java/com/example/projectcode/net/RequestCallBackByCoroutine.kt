package com.example.projectcode.net

import com.casual.baseframe.utils.RefreshLayout
import com.casual.baseframe.utils.ToastUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import java.net.ConnectException

class RequestCallBackByRxjava<T : ProjectData<*>> {
    constructor(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = WeakReference(compositeDisposable)
    }
    //所有引用外部对象的 都要用弱连接免得内存溢出
    private var compositeDisposable: WeakReference<CompositeDisposable>?
    var onSuccess: ((T?) -> Unit)? = null
    var onComplete: (() -> Unit)? = null
    var onFailed: ((error: String?, code: Int) -> Unit)? = null
    var refreshLayout: RefreshLayout? = null
    
    fun clean() {
        onSuccess = null
        onComplete = null
        onFailed = null
        refreshLayout = null
        //取消所有请求
        compositeDisposable?.get()?.clear()
        //清除弱连接
        compositeDisposable?.clear()
        compositeDisposable = null
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable?.get()?.add(disposable)
    }
}


fun <T : ProjectData<*>> Observable<T>.request(
    compositeDisposable: CompositeDisposable,
    init: RequestCallBackByRxjava<T>.() -> Unit
) {
    val callback = RequestCallBackByRxjava<T>(compositeDisposable = compositeDisposable)
    callback.init()
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<T> {
        override fun onComplete() {
            callback.clean()
        }

        override fun onSubscribe(d: Disposable) {
            callback.addDisposable(d)
        }

        override fun onNext(t: T) {
            callback.onSuccess?.invoke(t)
        }

        override fun onError(e: Throwable) {
            ToastUtils.showShort(e.message.toString())
        }
    }
    )
}


class RequestCallBackByCoroutine<T : ProjectData<*>> {
    //    var api: Deferred<T>? = null
    lateinit var api: (suspend CoroutineScope.() -> T)
    lateinit var apiRx: (Observable<T>)

    var onSuccess: ((T?) -> Unit)? = null
    var onComplete: (() -> Unit)? = null
    var onFailed: ((error: String?, code: Int) -> Unit)? = null
    var refreshLayout: RefreshLayout? = null
    fun clean() {
        onSuccess = null
        onComplete = null
        onFailed = null
        refreshLayout = null
    }
}

//kotlin  dsl技术
fun <T : ProjectData<*>> CoroutineScope.request(init: RequestCallBackByCoroutine<T>.() -> Unit) {
    val requestCallBack = RequestCallBackByCoroutine<T>()
    requestCallBack.init()
    //由于async会往上抛异常  所以要用这个来接受launch得到的异常
//    val handler = CoroutineExceptionHandler { _, exception ->
//        println("launch 捕捉到异常 $exception")
//    }
    async() {
        try {
            //启动模式 改成lazy  方便控制啥时候发起请求
            val work =
                async(Dispatchers.IO, start = CoroutineStart.LAZY, block = requestCallBack.api)
            val result = work.await()
            if (result.errorCode == 0) {
                withContext(Dispatchers.Main) {
                    requestCallBack.onSuccess?.invoke(result)
                    requestCallBack.refreshLayout?.isRefreshState?.let {
                        delay(500)
                        if (it) {
                            requestCallBack.refreshLayout?.previousIndex = 1
                            requestCallBack.refreshLayout?.finishRefresh(true)
                        } else {
                            requestCallBack.refreshLayout?.previousIndex =
                                requestCallBack.refreshLayout?.pageIndex!!
                            requestCallBack.refreshLayout?.finishLoadMore(true)
                        }
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    requestCallBack.onFailed?.invoke(result.errorMsg, result.errorCode)
                    ToastUtils.showShort(result.errorMsg ?: "哦豁----未知错误")
                    requestCallBack.refreshLayout?.isRefreshState?.let {
                        delay(500)
                        //失败了要获取上次的页码，比如当前页是3 刷新4的时候失败了，那就要把页面退回3
                        requestCallBack.refreshLayout?.pageIndex =
                            requestCallBack.refreshLayout?.previousIndex!!
                        if (it) {
                            requestCallBack.refreshLayout?.finishRefresh(false)
                        } else {
                            requestCallBack.refreshLayout?.finishLoadMore(false)
                        }
                    }
                }
            }
        } catch (e: ConnectException) {
            withContext(Dispatchers.Main) {
                requestCallBack.onFailed?.invoke("哦豁----网络连接出错", -1)
                ToastUtils.showShort("哦豁----网络连接出错")
                requestCallBack.refreshLayout?.isRefreshState?.let {
                    delay(500)
                    requestCallBack.refreshLayout?.pageIndex =
                        requestCallBack.refreshLayout?.previousIndex!!
                    if (it) {
                        requestCallBack.refreshLayout?.finishRefresh(false)
                    } else {
                        requestCallBack.refreshLayout?.finishLoadMore(false)
                    }
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                requestCallBack.onFailed?.invoke("哦豁----$e.message", -1)
                ToastUtils.showShort(e.message ?: "哦豁----未知错误")
                requestCallBack.refreshLayout?.isRefreshState?.let {
                    delay(500)
                    requestCallBack.refreshLayout?.pageIndex =
                        requestCallBack.refreshLayout?.previousIndex!!
                    if (it) {
                        requestCallBack.refreshLayout?.finishRefresh(false)
                    } else {
                        requestCallBack.refreshLayout?.finishLoadMore(false)
                    }
                }
            }
        } finally {
            requestCallBack.clean()
        }
    }
}