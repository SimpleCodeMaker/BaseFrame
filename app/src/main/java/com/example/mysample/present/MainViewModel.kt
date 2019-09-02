package com.example.mysample.present

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mysample.base.BaseModel
import com.example.mysample.base.request
import com.example.mysample.model.PublicNumberBean
import com.example.mysample.projectinterface.ProjectViewModel

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
        presenterScope.request<BaseModel<List<PublicNumberBean>>> {
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