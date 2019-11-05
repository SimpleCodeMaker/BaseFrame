package com.example.module_main.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.casual.baseframe.utils.RefreshLayout
import com.example.module_main.adapter.SecondAdapter
import com.example.projectcode.bean.DataX
import com.example.projectcode.bean.PublicNumberBean
import com.example.projectcode.bean.PublicNumberDetail
import com.example.projectcode.net.ProjectData
import com.example.projectcode.net.ProjectViewModel
import com.example.projectcode.net.request
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SecondViewModel : ProjectViewModel() {
    val adapter = SecondAdapter()
    var refreshLayout2: SmartRefreshLayout? = null
    var list: MutableLiveData<List<DataX?>>? = MutableLiveData()

    fun getData(pageindex: Int){
        serviceByRxjava.getPublicNumberDetailListByRxjava(408,pageindex).request(compositeDisposable){
            addRefreshLayout(refreshLayout2)
            onSuccess={
                list?.postValue(it?.data?.datas)
                isHasMore = (it?.data?.datas?.size?:0 ==20)
            }
        }
    }
    fun geta(pageindex: Int) {
//        presenterScope.request<ProjectData<PublicNumberDetail>> {
//            api = {
//                //如果有多个关联的请求，可以写一起 如下
////                val id = serviceByCoroutine.getPublicNumberList2().data?.get(0)?.id
////                serviceByCoroutine.getPublicNumberDetailList(id!!,pageindex)
//
//                //这个是写死一个请求
//                serviceByCoroutine.getPublicNumberDetailList(408,pageindex)
//
//            }
//            //如果关联上下拉的 需要传入这个
//            refreshLayout = refreshLayout2
//            onSuccess = {
//                list?.postValue(it!!.data?.datas)
//                Log.d("线程", Thread.currentThread().name)
//            }
//            onFailed = { error, code ->
//                Log.d("线程", Thread.currentThread().name)
//                Log.d("错误", error)
//            }
//        }
Observable.interval(0,1, TimeUnit.SECONDS)
    .flatMap {
        serviceByRxjava.getPublicNumberList3()
    }.request(compositeDisposable!!){
                onSuccess={
                Log.d("data",it?.data?.get(0)?.name)
            }
        }
        
        
//        serviceByRxjava.getPublicNumberList3()
//            .request(compositeDisposable!!){
//            onSuccess={
//                Log.d("data",it?.data?.get(0)?.name)
//            }
//        }

//        serviceByRxjava.getPublicNumberList3()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { 
//            
//        }
    }
}