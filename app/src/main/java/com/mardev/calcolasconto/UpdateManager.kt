package com.mardev.calcolasconto

import android.util.Log
import com.mardev.calcolasconto.data.Api
import com.mardev.calcolasconto.data.AppUpdateInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://raw.githubusercontent.com/o0Mardev/Calcola-Sconto/master/"

class UpdateManager() {

    /**
     * Return AppUpdateInfo or null if the call is not successful
     */
    private suspend fun getAppUpdateInfo(): AppUpdateInfo? {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val response = api.getAppUpdateInfo()

        return if (response.isSuccessful){
            response.body()
        } else null
    }

    suspend fun checkForAppUpdate() {
        try {
            val appUpdateInfo = getAppUpdateInfo()
            if (appUpdateInfo != null) {
                if (appUpdateInfo.latestVersionCode > BuildConfig.VERSION_CODE) {
                    Log.d("TAG", "getAppUpdateInfo: There's an update available")
                }
            }
        } catch (e: Exception){
            Log.e("TAG", "Error checking for update: ${e.message}")
        }
    }

}