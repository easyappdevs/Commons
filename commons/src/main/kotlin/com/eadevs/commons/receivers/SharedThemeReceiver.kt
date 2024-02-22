package com.eadevs.commons.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.eadevs.commons.extensions.baseConfig
import com.eadevs.commons.extensions.checkAppIconColor
import com.eadevs.commons.extensions.getSharedTheme
import com.eadevs.commons.helpers.MyContentProvider

class SharedThemeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.baseConfig.apply {
            val oldColor = appIconColor
            if (intent.action == MyContentProvider.SHARED_THEME_ACTIVATED) {
                if (!wasSharedThemeForced) {
                    wasSharedThemeForced = true
                    isUsingSharedTheme = true
                    wasSharedThemeEverActivated = true

                    context.getSharedTheme {
                        if (it != null) {
                            textColor = it.textColor
                            backgroundColor = it.backgroundColor
                            primaryColor = it.primaryColor
                            accentColor = it.accentColor
                            appIconColor = it.appIconColor
                            checkAppIconColorChanged(oldColor, appIconColor, context)
                        }
                    }
                }
            } else if (intent.action == MyContentProvider.SHARED_THEME_UPDATED) {
                if (isUsingSharedTheme) {
                    context.getSharedTheme {
                        if (it != null) {
                            textColor = it.textColor
                            backgroundColor = it.backgroundColor
                            primaryColor = it.primaryColor
                            accentColor = it.accentColor
                            appIconColor = it.appIconColor
                            checkAppIconColorChanged(oldColor, appIconColor, context)
                        }
                    }
                }
            }
        }
    }

    private fun checkAppIconColorChanged(oldColor: Int, newColor: Int, context: Context) {
        if (oldColor != newColor) {
            context.checkAppIconColor()
        }
    }
}
