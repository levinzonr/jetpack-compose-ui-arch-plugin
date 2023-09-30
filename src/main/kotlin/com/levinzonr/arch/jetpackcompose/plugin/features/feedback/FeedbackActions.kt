package com.levinzonr.arch.jetpackcompose.plugin.features.feedback

import com.intellij.ide.BrowserUtil
import com.intellij.ui.dsl.builder.Row


fun Row.feedbackActions() {
    link("\uD83D\uDD27 Bug report") {
        BrowserUtil.open("https://github.com/levinzonr/jetpack-compose-ui-arch-plugin/issues/new?assignees=&labels=&projects=&template=bug_report.md&title=")
    }
    link("\uD83D\uDE4B Feature request") {
        BrowserUtil.open("https://github.com/levinzonr/jetpack-compose-ui-arch-plugin/issues/new?assignees=&labels=&projects=&template=feature_request.md&title=")

    }
}