package com.mardev.calcolasconto.domain.use_case

import com.mardev.calcolasconto.BuildConfig
import com.mardev.calcolasconto.common.Resource
import com.mardev.calcolasconto.domain.model.AppUpdateInfo
import com.mardev.calcolasconto.domain.repository.AppUpdateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class IsUpdateAvailableUseCase @Inject constructor(
    private val repository: AppUpdateRepository
) {
    suspend operator fun invoke(): Flow<Resource<Boolean>> {
        return repository.getAppUpdateInfo().transform<Resource<AppUpdateInfo>, Resource<Boolean>> { appUpdateInfoResource ->
            when (appUpdateInfoResource) {
                is Resource.Success -> {
                    appUpdateInfoResource.data?.let {
                        emit(Resource.Success(it.latestVersionCode > BuildConfig.VERSION_CODE))
                    }
                }

                is Resource.Error -> {
                    appUpdateInfoResource.uiText?.let {
                        emit(Resource.Error(it))
                    }
                }

                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
            }
        }
    }
}