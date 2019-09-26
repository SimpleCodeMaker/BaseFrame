package com.example.projectcode.net

import com.casual.baseframe.http.BaseHttp
import com.casual.baseframe.base.BFModel

abstract class ProjectViewModel : BFModel() {
    protected val serviceByCoroutine: Api by lazy {
        BaseHttp.getServiceByCoroutine<Api>()
    }
    
    protected val serviceByRxjava: Api by lazy {
        BaseHttp.getServiceByRxjava<Api>()
    }
    
}