package com.example.projectcode.net

import com.casual.baseframe.utils.ToastUtils
import com.example.projectcode.R
import com.example.projectcode.RouteUris
import com.scwang.smartrefresh.layout.api.RefreshLayout
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class RequestCallBackByRxjava<T : ProjectData<*>>
{
    constructor(compositeDisposable: CompositeDisposable)
    {
        this.compositeDisposable = WeakReference(compositeDisposable)
    }

    private var compositeDisposable: WeakReference<CompositeDisposable>?
    var refreshLayout: WeakReference<RefreshLayout?>? = null
    var onSuccess: ((T?) -> Unit)? = null
    var onComplete: (() -> Unit)? = null
    var onFailed: ((error: String?, code: Int) -> Unit)? = null
    var isShowLoading: Boolean = false
//    var loading: CustomDialog.Builder? = null
    var isHasMore = false

    fun addRefreshLayout(refreshLayout: RefreshLayout?)
    {
        this.refreshLayout = WeakReference(refreshLayout)
    }

    fun clean()
    {
        //清空回调
        onSuccess = null
        onComplete = null
        onFailed = null

//        loading = null
        //除去刷新控件
        refreshLayout?.clear()
        refreshLayout = null

    }

    fun stop()
    {
        clean()
        compositeDisposable?.get()?.clear()
        compositeDisposable?.clear()
        compositeDisposable = null
    }

    //这里是刷新回调，如果没有给refreshLayout赋值不会生效
    fun finishRefreshLoad(isHasMore: Boolean = false)
    {
        refreshLayout?.get()?.finishRefresh()
        refreshLayout?.get()?.finishLoadMore(300, true, !isHasMore)
    }


    fun addDisposable(disposable: Disposable)
    {
        compositeDisposable?.get()?.add(disposable)
    }
}

//分割了============================================================
fun <T : ProjectData<*>> Observable<T>.request(
        compositeDisposable: CompositeDisposable,
        init: RequestCallBackByRxjava<T>.() -> Unit
)
{
    val callback = RequestCallBackByRxjava<T>(compositeDisposable = compositeDisposable)
    callback.init()
    subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T>
            {
                override fun onComplete()
                {
                    //这里是刷新回调，如果没有给refreshLayout赋值不会生效
                    callback.finishRefreshLoad(callback.isHasMore)
//                    callback.loading?.dismiss()
                    callback.clean()
                }

                override fun onSubscribe(d: Disposable)
                {
//                    if (callback.isShowLoading)
//                    {
//                        getTopActivity(sContext)?.let {
//                            callback.loading = CustomDialog.Builder(getTopActivity(sContext)!!)
//                                    .setBackGroundTransparent()
//                                    .setContentView(R.layout.dialog_loding)
//                                    .setCancelable(false)
//                        }
//
//                        callback.loading?.setDissmissLister {
//                            if (!d.isDisposed)
//                            {
//                                d.dispose()
//                            }
//                        }?.show()
//                    }
                    callback.addDisposable(d)
                }

                override fun onNext(t: T)
                {
                    callback.onSuccess?.invoke(t)
//                    when (t.code)
//                    {
//                        //成功
//                        0 ->
//                        {
//                            callback.onSuccess?.invoke(t)
//                        }
//                        //返回401 表示另一处登录，需要重新登录
//                        401 ->
//                        {
//                            callback.finishRefreshLoad()
//                            callback.clean()
//                            callback.stop()
//                            //退出环信
//                            EMClient.getInstance().logout(true)
//                            val userService = UserService.getInstance()
//                            val pushService = PushService.getInstance()
//                            //删除用户信息
//                            userService.clearAllMsg()
//                            //删除极光推送
//                            pushService.deleteAlias(sContext)
//                            getTopActivity(sContext)?.let {
//                                CustomDialog.Builder(it)
//                                        .setContentView(R.layout.dialog_tips_layout)
//                                        .setText(R.id.tv_title, "提示")
//                                        .setCancelable(false)
//                                        .setText(R.id.tv_content, "您的登录信息已失效，请重新登录")
//                                        .addTextAction(R.id.tv_commit, "确定") {
//                                            //退出所有activity
//                                            finishActivity()
//                                            RouteUris.Activitys.login.doRoute()
//                                        }
//                                        .show()
//                            }
//                        }
//                        else ->
//                        {
//                            callback.finishRefreshLoad()
//                            callback.clean()
//                            callback.stop()
//                            ToastUtils.showShort("Tips:${t.msg}")
//                        }
//                    }
                }

                override fun onError(e: Throwable)
                {
//                    callback.loading?.dismiss()
                    callback.finishRefreshLoad()
                    callback.stop()
                    ToastUtils.showShort("Tips:${e.message}")
                }
            }
            )
}