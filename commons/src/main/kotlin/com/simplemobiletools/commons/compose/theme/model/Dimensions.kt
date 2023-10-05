package com.simplemobiletools.commons.compose.theme.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
data class Dimensions(
    val margin: Margins,
    val icon: IconSizes
) {
    @Immutable
    data class Margins(
        val extraSmall: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val extraLarge: Dp,
    )

    @Immutable
    data class IconSizes(
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val extraLarge: Dp,
    )
}
