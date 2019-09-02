package com.example.mysample.present

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.baselibrary.utils.RefreshLayout
import com.example.mysample.adapter.SecondAdapter
import com.example.mysample.base.BaseModel
import com.example.mysample.base.request
import com.example.mysample.model.DataX
import com.example.mysample.model.PublicNumberBean
import com.example.mysample.model.PublicNumberDetail
import com.example.mysample.projectinterface.ProjectViewModel

class SecondViewModel : ProjectViewModel() {
    val adapter = SecondAdapter()
    var refreshLayout2: RefreshLayout? = null
    var list: MutableLiveData<List<DataX?>>? = MutableLiveData()
    fun geta(pageindex: Int) {
        presenterScope.request<BaseModel<PublicNumberDetail>> {
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