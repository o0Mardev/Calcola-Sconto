package com.mardev.calcolasconto

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.util.Log
import androidx.core.content.FileProvider
import com.mardev.calcolasconto.data.Api
import com.mardev.calcolasconto.data.AppUpdateInfo
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream


private const val BASE_URL = "https://raw.githubusercontent.com/o0Mardev/Calcola-Sconto/master/"


class UpdateManager(private val context: Context) {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    private var downloadUrl: String? = null

    suspend fun checkForAppUpdate(): Boolean {
        try {
            val appUpdateInfo = getAppUpdateInfo()
            if (appUpdateInfo != null) {
                Log.d("TAG", "checkForAppUpdate: appUpdateInfo=$appUpdateInfo")
                if (appUpdateInfo.latestVersionCode > BuildConfig.VERSION_CODE) {
                    Log.d("TAG", "getAppUpdateInfo: There's an update available")
                    downloadUrl = appUpdateInfo.downloadUrl
                    return true
                } else {
                    Log.d("TAG", "checkForAppUpdate: There's no update available")
                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "Error checking for update: ${e.message}")
        }
        return false
    }

    suspend fun update(){
        Log.d("TAG", "update: function called")
        val url = downloadUrl
        if (url!=null){
            val update = downloadUpdate(url)
            if (update != null) {
                val file = saveUpdateFile(update)
                installUpdateFile(file)
            }
        }
    }

    private suspend fun getAppUpdateInfo(): AppUpdateInfo? {
        val response = api.getAppUpdateInfo()
        return if (response.isSuccessful) {
            response.body()
        } else null
    }
    private suspend fun downloadUpdate(fileUrl: String): ResponseBody? {
        val response = api.downloadFile(fileUrl)
        return response.body()
    }


    private fun saveUpdateFile(body: ResponseBody): File {
        val dir = context.filesDir
        val fileName = "update.apk"
        val file = File(dir, fileName)

        Log.d("TAG", "saveUpdateFile: dir $dir")
        Log.d("TAG", "saveUpdateFile: body is not null")

        val inputStream = body.byteStream()
        val outputStream = FileOutputStream(file)

        inputStream.use { input ->
            Log.d("TAG", "saveUpdateFile: INPUT $input")
            outputStream.use { output ->
                Log.d("TAG", "saveUpdateFile: OUTPUT $output")
                input.copyTo(output)
            }
        }
        return file
    }

    private fun installUpdateFile(file: File) {
        if (file.exists()) {
            Log.d("TAG", "installUpdateFile: The file exists")

            val uri = FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + ".provider",
                file
            )

            Log.d("TAG", "installUpdateFile: uri $uri")

            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "application/vnd.android.package-archive")
            intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_GRANT_READ_URI_PERMISSION

            Log.d("TAG", "installUpdateFile: intent $intent")
            context.startActivity(intent)
        }
    }

}