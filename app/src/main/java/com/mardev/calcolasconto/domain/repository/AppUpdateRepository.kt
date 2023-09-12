package com.mardev.calcolasconto.domain.repository

import com.mardev.calcolasconto.common.Resource
import com.mardev.calcolasconto.domain.model.AppUpdateInfo
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface AppUpdateRepository {

    suspend fun getAppUpdateInfo(): Flow<Resource<AppUpdateInfo>>

    suspend fun getAppUpdateApk(url: String): Flow<Resource<ResponseBody>>

}