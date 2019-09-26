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
//        presenterScope.request<ProjectData<List<PublicNumberBean>>> {
//            api = { serviceByCoroutine.getPublicNumberList2() }
//            onSuccess={
//                list?.postValue(it!!.data)
//                Log.d("线程", Thread.currentThread().name)
//            }
//            onFailed={ error, code ->
//                Log.d("线程", Thread.currentThread().name)
//                Log.d("错误", error)
//            }
//        }
        
//       serviceByRxjava.getPublicNumberList3().request(compositeDisposable!!){
//           onSuccess={
//               list?.postValue(it!!.data)
//           }
//       }
        
    }

}