package com.eadevs.commons.samples.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.eadevs.commons.activities.BaseSimpleActivity
import com.eadevs.commons.activities.ManageBlockedNumbersActivity
import com.eadevs.commons.compose.alert_dialog.AlertDialogState
import com.eadevs.commons.compose.alert_dialog.rememberAlertDialogState
import com.eadevs.commons.compose.extensions.*
import com.eadevs.commons.compose.theme.AppThemeSurface
import com.eadevs.commons.dialogs.ConfirmationDialog
import com.eadevs.commons.dialogs.DonateAlertDialog
import com.eadevs.commons.dialogs.RateStarsAlertDialog
import com.eadevs.commons.extensions.appLaunched
import com.eadevs.commons.extensions.hideKeyboard
import com.eadevs.commons.extensions.launchMoreAppsFromUsIntent
import com.eadevs.commons.extensions.launchViewIntent
import com.eadevs.commons.helpers.*
import com.eadevs.commons.models.FAQItem
import com.eadevs.commons.samples.BuildConfig
import com.eadevs.commons.samples.R
import com.eadevs.commons.samples.screens.MainScreen

class MainActivity : BaseSimpleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appLaunched(BuildConfig.APPLICATION_ID)
        enableEdgeToEdgeSimple()
        setContent {
            AppThemeSurface {
                val showMoreApps = onEventValue { !resources.getBoolean(com.eadevs.commons.R.bool.hide_google_relations) }

                MainScreen(
                    openColorCustomization = ::startCustomizationActivity,
                    manageBlockedNumbers = {
                        startActivity(Intent(this@MainActivity, ManageBlockedNumbersActivity::class.java))
                    },
                    showComposeDialogs = {
                        startActivity(Intent(this@MainActivity, TestDialogActivity::class.java))
                    },
                    openTestButton = {
                        ConfirmationDialog(
                            this@MainActivity,
                            FAKE_VERSION_APP_LABEL,
                            positive = com.eadevs.commons.R.string.ok,
                            negative = 0
                        ) {
                            launchViewIntent(DEVELOPER_PLAY_STORE_URL)
                        }
                    },
                    showMoreApps = showMoreApps,
                    openAbout = ::launchAbout,
                    moreAppsFromUs = ::launchMoreAppsFromUsIntent
                )
                AppLaunched()
            }
        }
    }

    @Composable
    private fun AppLaunched(
        donateAlertDialogState: AlertDialogState = getDonateAlertDialogState(),
        rateStarsAlertDialogState: AlertDialogState = getRateStarsAlertDialogState(),
    ) {
        LaunchedEffect(Unit) {
            appLaunchedCompose(
                appId = BuildConfig.APPLICATION_ID,
                showDonateDialog = donateAlertDialogState::show,
                showRateUsDialog = rateStarsAlertDialogState::show,
                showUpgradeDialog = {}
            )
        }
    }

    @Composable
    private fun getDonateAlertDialogState() =
        rememberAlertDialogState().apply {
            DialogMember {
                DonateAlertDialog(alertDialogState = this)
            }
        }

    @Composable
    private fun getRateStarsAlertDialogState() = rememberAlertDialogState().apply {
        DialogMember {
            RateStarsAlertDialog(alertDialogState = this, onRating = ::rateStarsRedirectAndThankYou)
        }
    }

    private fun launchAbout() {
        val licenses = LICENSE_AUTOFITTEXTVIEW

        val faqItems = arrayListOf(
            FAQItem(com.eadevs.commons.R.string.faq_1_title_commons, com.eadevs.commons.R.string.faq_1_text_commons),
            FAQItem(com.eadevs.commons.R.string.faq_1_title_commons, com.eadevs.commons.R.string.faq_1_text_commons),
            FAQItem(com.eadevs.commons.R.string.faq_4_title_commons, com.eadevs.commons.R.string.faq_4_text_commons)
        )

        if (!resources.getBoolean(com.eadevs.commons.R.bool.hide_google_relations)) {
            faqItems.add(FAQItem(com.eadevs.commons.R.string.faq_2_title_commons, com.eadevs.commons.R.string.faq_2_text_commons))
            faqItems.add(FAQItem(com.eadevs.commons.R.string.faq_6_title_commons, com.eadevs.commons.R.string.faq_6_text_commons))
        }

        startAboutActivity(R.string.commons_app_name, licenses, BuildConfig.VERSION_NAME, faqItems, true)
    }

    private fun startAboutActivity(
        appNameId: Int, licenseMask: Long, versionName: String, faqItems: ArrayList<FAQItem>, showFAQBeforeMail: Boolean,
        getAppIconIDs: ArrayList<Int> = getAppIconIDs(),
        getAppLauncherName: String = getAppLauncherName()
    ) {
        hideKeyboard()
        Intent(applicationContext, com.eadevs.commons.activities.AboutActivity::class.java).apply {
            putExtra(APP_ICON_IDS, getAppIconIDs)
            putExtra(APP_LAUNCHER_NAME, getAppLauncherName)
            putExtra(APP_NAME, getString(appNameId))
            putExtra(APP_LICENSES, licenseMask)
            putExtra(APP_VERSION_NAME, versionName)
            putExtra(APP_FAQ, faqItems)
            putExtra(SHOW_FAQ_BEFORE_MAIL, showFAQBeforeMail)
            startActivity(this)
        }
    }

    override fun getAppLauncherName() = getString(R.string.commons_app_name)

    override fun getAppIconIDs() = arrayListOf(
        R.mipmap.ic_launcher_red,
        R.mipmap.ic_launcher_pink,
        R.mipmap.ic_launcher_purple,
        R.mipmap.ic_launcher_deep_purple,
        R.mipmap.ic_launcher_indigo,
        R.mipmap.ic_launcher_blue,
        R.mipmap.ic_launcher_light_blue,
        R.mipmap.ic_launcher_cyan,
        R.mipmap.ic_launcher_teal,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher_light_green,
        R.mipmap.ic_launcher_lime,
        R.mipmap.ic_launcher_yellow,
        R.mipmap.ic_launcher_amber,
        R.mipmap.ic_launcher_orange,
        R.mipmap.ic_launcher_deep_orange,
        R.mipmap.ic_launcher_brown,
        R.mipmap.ic_launcher_blue_grey,
        R.mipmap.ic_launcher_grey_black
    )
}
