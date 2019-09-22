package com.example.module_main.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.projectcode.bean.PublicNumberBean
import com.example.projectcode.net.ProjectData
import com.example.projectcode.net.ProjectViewModel
import com.example.projectcode.net.request

class MainViewModel : ProjectViewModel() {
    var list: MutableLiveData<List<PublicNumberBean>>? = MutableLiveData()
    fun geta() {

//        presenterScope.request<BaseModel<List<PublicNumberBean>>> {
//            api = service.getPublicNumberList()
//            onSuccess {
//                list?.postValue(it!!.data)
//                Log.d("线程", Thread.currentThread().name)
//            }
//            onFailed { error, code ->
//                Log.d("线程", Thread.currentThread().name)
//                Log.d("错误", error)
//            }
//        }
        presenterScope.request<ProjectData<List<PublicNumberBean>>> {
            api = { service.getPublicNumberList2() }
            onSuccess={
                list?.postValue(it!!.data)
                Log.d("线程", Thread.currentThread().name)
            }
            onFailed={ error, code ->
                Log.d("线程", Thread.currentThread().name)
                Log.d("错误", error)
            }
        }
    }

}