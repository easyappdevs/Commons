package com.eadevs.commons.compose.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.eadevs.commons.R
import com.eadevs.commons.compose.extensions.MyDevices
import com.eadevs.commons.compose.lists.SimpleColumnScaffold
import com.eadevs.commons.compose.settings.SettingsGroup
import com.eadevs.commons.compose.settings.SettingsHorizontalDivider
import com.eadevs.commons.compose.settings.SettingsListItem
import com.eadevs.commons.compose.settings.SettingsTitleTextComponent
import com.eadevs.commons.compose.theme.AppThemeSurface
import com.eadevs.commons.compose.theme.SimpleTheme

private val startingTitlePadding = Modifier.padding(start = 56.dp)

@Composable
internal fun AboutScreen(
    goBack: () -> Unit,
    helpUsSection: @Composable () -> Unit,
    aboutSection: @Composable () -> Unit,
    otherSection: @Composable () -> Unit,
) {
    SimpleColumnScaffold(title = stringResource(id = R.string.about), goBack = goBack) {
        aboutSection()
        helpUsSection()
        otherSection()
//        SettingsListItem(text = stringResource(id = R.string.about_footer))
    }
}

@Composable
internal fun HelpUsSection(
    onRateUsClick: () -> Unit,
    onInviteClick: () -> Unit,
    showRateUs: Boolean,
    showInvite: Boolean,
) {
    SettingsGroup(title = {
        SettingsTitleTextComponent(text = stringResource(id = R.string.help_us), modifier = startingTitlePadding)
    }) {
        if (showRateUs) {
            TwoLinerTextItem(text = stringResource(id = R.string.rate_us), icon = R.drawable.ic_star_vector, click = onRateUsClick)
        }
        if (showInvite) {
            TwoLinerTextItem(text = stringResource(id = R.string.invite_friends), icon = R.drawable.ic_add_person_vector, click = onInviteClick)
        }
        SettingsHorizontalDivider()
    }
}

@Composable
internal fun OtherSection(
    showPrivacyPolicy: Boolean,
    onPrivacyPolicyClick: () -> Unit,
    onLicenseClick: () -> Unit,
    version: String,
    onVersionClick: () -> Unit,
) {
    SettingsGroup(title = {
        SettingsTitleTextComponent(text = stringResource(id = R.string.other), modifier = startingTitlePadding)
    }) {
        if (showPrivacyPolicy) {
            TwoLinerTextItem(
                click = onPrivacyPolicyClick,
                text = stringResource(id = R.string.privacy_policy),
                icon = R.drawable.ic_unhide_vector
            )
        }
        TwoLinerTextItem(
            click = onLicenseClick,
            text = stringResource(id = R.string.third_party_licences),
            icon = R.drawable.ic_article_vector
        )
        TwoLinerTextItem(
            click = onVersionClick,
            text = version,
            icon = R.drawable.ic_info_vector
        )
        SettingsHorizontalDivider()
    }
}


@Composable
internal fun AboutSection(
    setupFAQ: Boolean,
    onFAQClick: () -> Unit,
    onEmailClick: () -> Unit
) {
    SettingsGroup(title = {
        SettingsTitleTextComponent(text = stringResource(id = R.string.support), modifier = startingTitlePadding)
    }) {
        if (setupFAQ) {
            TwoLinerTextItem(
                click = onFAQClick,
                text = stringResource(id = R.string.frequently_asked_questions),
                icon = R.drawable.ic_question_mark_vector
            )
        }
        TwoLinerTextItem(
            click = onEmailClick,
            text = stringResource(id = R.string.my_email),
            icon = R.drawable.ic_mail_vector
        )
        SettingsHorizontalDivider()
    }
}

@Composable
internal fun TwoLinerTextItem(text: String, icon: Int, click: () -> Unit) {
    SettingsListItem(
        tint = SimpleTheme.colorScheme.onSurface,
        click = click,
        text = text,
        icon = icon,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@MyDevices
@Composable
private fun AboutScreenPreview() {
    AppThemeSurface {
        AboutScreen(
            goBack = {},
            helpUsSection = {
                HelpUsSection(
                    onRateUsClick = {},
                    onInviteClick = {},
                    showRateUs = true,
                    showInvite = true,
                )
            },
            aboutSection = {
                AboutSection(setupFAQ = true, onFAQClick = {}, onEmailClick = {})
            },
        ) {
            OtherSection(
                showPrivacyPolicy = true,
                onPrivacyPolicyClick = {},
                onLicenseClick = {},
                version = "5.0.4",
                onVersionClick = {}
            )
        }
    }
}
