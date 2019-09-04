package com.casual.mysample.base

import com.casual.mysample.model.PublicNumberBean
import com.casual.mysample.model.PublicNumberDetail
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface BaseService {
    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/chapters/json")
    fun getPublicNumberList(): Deferred<BaseModel<List<PublicNumberBean>>>

    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getPublicNumberList2(): BaseModel<List<PublicNumberBean>>

    /**
     * 查看某个公众号历史数据
     */
    @GET("/wxarticle/list/{ID}/{pageindex}/json")
    suspend fun getPublicNumberDetailList(@Path("ID") ID: Int, @Path("pageindex") pageindex: Int): BaseModel<PublicNumberDetail>
}

