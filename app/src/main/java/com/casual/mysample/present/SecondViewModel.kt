package com.casual.mysample.present

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.casual.baseframe.utils.RefreshLayout
import com.casual.mysample.adapter.SecondAdapter
import com.casual.mysample.base.BaseModel
import com.casual.mysample.base.request
import com.casual.mysample.model.DataX
import com.casual.mysample.model.PublicNumberDetail
import com.casual.mysample.projectinterface.ProjectViewModel

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