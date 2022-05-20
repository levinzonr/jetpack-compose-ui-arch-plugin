package com.levinzonr.arch.jetpackcompose.plugin.base

import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.Dependencies
import javax.swing.JComponent

abstract class BaseDialog: DialogWrapper(true) {

    lateinit var panel: DialogPanel
    protected val dialogScope = Dependencies.mainScope


    override fun createCenterPanel(): JComponent? {
        panel = createPanel()
        return panel
    }

    abstract fun createPanel() : DialogPanel
}