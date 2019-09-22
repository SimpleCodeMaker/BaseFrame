package com.example.module_main.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.casual.baseframe.utils.RefreshLayout
import com.example.module_main.adapter.SecondAdapter
import com.example.projectcode.bean.DataX
import com.example.projectcode.bean.PublicNumberDetail
import com.example.projectcode.net.ProjectData
import com.example.projectcode.net.ProjectViewModel
import com.example.projectcode.net.request

class SecondViewModel : ProjectViewModel() {
    val adapter = SecondAdapter()
    var refreshLayout2: RefreshLayout? = null
    var list: MutableLiveData<List<DataX?>>? = MutableLiveData()
    fun geta(pageindex: Int) {
        presenterScope.request<ProjectData<PublicNumberDetail>> {
            api = {
                //如果有多个关联的请求，可以写一起 如下
//                val id = service.getPublicNumberList2().data?.get(0)?.id
//                service.getPublicNumberDetailList(id!!,pageindex)

                //这个是写死一个请求
                service.getPublicNumberDetailList(408,pageindex)

            }
            //如果关联上下拉的 需要传入这个
            refreshLayout = refreshLayout2
            onSuccess = {
                list?.postValue(it!!.data?.datas)
                Log.d("线程", Thread.currentThread().name)
            }
            onFailed = { error, code ->
                Log.d("线程", Thread.currentThread().name)
                Log.d("错误", error)
            }
        }
    }
}