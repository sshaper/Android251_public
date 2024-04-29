package com.ebookfrenzy.themedemo

import android.app.Application
import com.google.android.material.color.DynamicColors

class ThemeDemoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}