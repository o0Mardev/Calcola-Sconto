package com.mardev.calcolasconto.domain.use_case

import com.mardev.calcolasconto.common.Resource
import com.mardev.calcolasconto.domain.repository.AppUpdateRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject

class DownloadAppUpdateUseCase @Inject constructor(
    private val repository: AppUpdateRepository
) {
    suspend operator fun invoke(url: String): Flow<Resource<ResponseBody>> {
            return repository.getAppUpdateApk(url)
    }
}