package com.eadevs.commons.extensions

import android.content.Context
import com.eadevs.commons.models.FileDirItem

fun FileDirItem.isRecycleBinPath(context: Context): Boolean {
    return path.startsWith(context.recycleBinPath)
}
