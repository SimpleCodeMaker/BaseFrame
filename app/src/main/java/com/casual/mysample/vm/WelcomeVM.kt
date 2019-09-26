package com.casual.mysample.vm

import com.example.projectcode.net.ProjectViewModel

class WelcomeVM : ProjectViewModel() {
//    var list: MutableLiveData<List<PublicNumberBean>>? = MutableLiveData()
//    fun geta() {
//
////        presenterScope.request<BaseModel<List<PublicNumberBean>>> {
////            api = serviceByCoroutine.getPublicNumberList()
////            onSuccess {
////                list?.postValue(it!!.data)
////                Log.d("线程", Thread.currentThread().name)
////            }
////            onFailed { error, code ->
////                Log.d("线程", Thread.currentThread().name)
////                Log.d("错误", error)
////            }
////        }
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
//    }

}