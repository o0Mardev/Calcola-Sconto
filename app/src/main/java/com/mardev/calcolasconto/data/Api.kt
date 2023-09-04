package com.mardev.calcolasconto.data

import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("app-update-info.json")
    suspend fun getAppUpdateInfo(): Response<AppUpdateInfo>
}