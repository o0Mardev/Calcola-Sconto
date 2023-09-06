package com.mardev.calcolasconto

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This is used by dagger hilt for injecting application context
 */
@HiltAndroidApp
class BaseApplication: Application()