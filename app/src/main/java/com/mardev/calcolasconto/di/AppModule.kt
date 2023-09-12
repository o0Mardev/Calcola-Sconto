package com.mardev.calcolasconto.di

import android.content.Context
import com.mardev.calcolasconto.common.Constants.BASE_URL
import com.mardev.calcolasconto.data.remote.AppUpdateApi
import com.mardev.calcolasconto.data.repository.AppUpdateRepositoryImpl
import com.mardev.calcolasconto.domain.repository.DataStoreRepository
import com.mardev.calcolasconto.data.repository.DataStoreRepositoryImpl
import com.mardev.calcolasconto.domain.repository.AppUpdateRepository
import com.mardev.calcolasconto.domain.use_case.DownloadAppUpdateUseCase
import com.mardev.calcolasconto.domain.use_case.GetUpdateUrlUseCase
import com.mardev.calcolasconto.domain.use_case.SaveUpdateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(app)

    @Singleton
    @Provides
    fun provideAppUpdateApi(): AppUpdateApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppUpdateApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAppUpdateRepository(api: AppUpdateApi): AppUpdateRepository {
        return AppUpdateRepositoryImpl(api)
    }


    @Singleton
    @Provides
    fun provideSaveUpdateUseCase(): SaveUpdateUseCase{
        return SaveUpdateUseCase()
    }


    @Singleton
    @Provides
    fun provideDownloadAppUpdateUseCase(repository: AppUpdateRepository): DownloadAppUpdateUseCase {
        return DownloadAppUpdateUseCase(repository)
    }


    @Singleton
    @Provides
    fun provideGetUpdateUrlUseCase(repository: AppUpdateRepository): GetUpdateUrlUseCase {
        return GetUpdateUrlUseCase(repository)
    }
}