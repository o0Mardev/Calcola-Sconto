package com.mardev.calcolasconto.data.repository

import com.mardev.calcolasconto.R
import com.mardev.calcolasconto.common.Resource
import com.mardev.calcolasconto.common.UiText
import com.mardev.calcolasconto.data.remote.AppUpdateApi
import com.mardev.calcolasconto.domain.model.AppUpdateInfo
import com.mardev.calcolasconto.domain.repository.AppUpdateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AppUpdateRepositoryImpl @Inject constructor(
    private val api: AppUpdateApi
) : AppUpdateRepository {
    override suspend fun getAppUpdateInfo(): Flow<Resource<AppUpdateInfo>> {
        return flow {
            try {
                emit(Resource.Loading())
                val appUpdateInfo = api.getAppUpdateInfo()
                emit(Resource.Success(appUpdateInfo))
            } catch (e: IOException) {
                emit(
                    Resource.Error(UiText.StringResource(R.string.connection_error))
                )
            } catch (e: HttpException) {
                emit(
                    Resource.Error(UiText.StringResource(R.string.server_error))
                )
            }
        }
    }

    override suspend fun getAppUpdateApk(url: String): Flow<Resource<ResponseBody>> {
        return flow {
            try {
                emit(Resource.Loading())
                val responseBody = api.downloadFile(url)
                emit(Resource.Success(responseBody))
            } catch (e: IOException) {
                emit(
                    Resource.Error(UiText.StringResource(R.string.connection_error))
                )
            } catch (e: HttpException) {
                emit(
                    Resource.Error(UiText.StringResource(R.string.server_error))
                )
            }
        }
    }

}