package com.eadevs.commons.compose.extensions

import android.app.Activity
import android.content.Context
import com.eadevs.commons.R
import com.eadevs.commons.extensions.baseConfig
import com.eadevs.commons.extensions.redirectToRateUs
import com.eadevs.commons.extensions.toast
import com.eadevs.commons.helpers.BaseConfig

val Context.config: BaseConfig get() = BaseConfig.newInstance(applicationContext)

fun Activity.rateStarsRedirectAndThankYou(stars: Int) {
    if (stars == 5) {
        redirectToRateUs()
    }
    toast(R.string.thank_you)
    baseConfig.wasAppRated = true
}
