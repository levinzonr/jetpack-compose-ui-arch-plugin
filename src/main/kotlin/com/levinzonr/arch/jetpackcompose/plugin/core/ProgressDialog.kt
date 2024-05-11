package com.levinzonr.arch.jetpackcompose.plugin.core

import com.intellij.openapi.progress.util.ProgressWindow
import com.intellij.openapi.project.ProjectManager

class ProgressDialog(
    private val title: String,
    private val text: String
) {
    private val project by lazy { ProjectManager.getInstance().defaultProject }
    private var currentDialog: ProgressWindow? = null

    fun show() {
        currentDialog = createDialog().apply {
            start()
        }
    }

    fun hide() {
        currentDialog?.stop()
        currentDialog = null
    }


    private fun createDialog() = ProgressWindow(false, project).apply {
        this.title = this@ProgressDialog.title
        this.text = this@ProgressDialog.text
        this.text2 = this@ProgressDialog.text
    }
}