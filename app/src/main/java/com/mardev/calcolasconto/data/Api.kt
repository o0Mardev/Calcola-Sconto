package com.mardev.calcolasconto.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface Api {
    @GET("app-update-info.json")
    suspend fun getAppUpdateInfo(): Response<AppUpdateInfo>

    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>
}