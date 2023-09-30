package com.levinzonr.arch.jetpackcompose.plugin.features.feedback

import com.intellij.ide.BrowserUtil
import com.intellij.ui.dsl.builder.Row
import com.levinzonr.arch.jetpackcompose.plugin.core.Links


fun Row.feedbackActions() {
    link("\uD83D\uDD27 Bug report") {
        BrowserUtil.open(Links.BUG_REPORT)
    }
    link("\uD83D\uDE4B Feature request") {
        BrowserUtil.open(Links.FEATURE_REQUEST)
    }
}