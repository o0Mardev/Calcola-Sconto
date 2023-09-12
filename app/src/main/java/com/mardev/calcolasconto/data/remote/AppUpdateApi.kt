package com.mardev.calcolasconto.data.remote

import com.mardev.calcolasconto.domain.model.AppUpdateInfo
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface AppUpdateApi {
    @GET("app-update-info.json")
    suspend fun getAppUpdateInfo(): AppUpdateInfo

    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl:String): ResponseBody
}