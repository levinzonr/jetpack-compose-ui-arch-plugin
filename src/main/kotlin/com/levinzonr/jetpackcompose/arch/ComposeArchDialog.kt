package com.levinzonr.jetpackcompose.arch

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.layout.panel
import com.levinzonr.jetpackcompose.arch.extensions.textField
import javax.swing.JComponent

class ComposeArchDialog(
    project: Project
) : DialogWrapper(
    project,
    null,
    true,
    IdeModalityType.MODELESS,
    false
) {
    init { init() }

    var name: String = "Heello"
        set(value) {
            println(value)
            field = value
        }


    var onOk: (String) -> Unit = {}

    override fun createCenterPanel(): JComponent {
        return panel {
            row { label("New Compose Screen") }
            row { textField { name = it }.focused() }
            row { button("Ok") { onOk.invoke(name)} }
        }
    }




    fun show(onOk: (String) -> Unit) {
        this.onOk = onOk
        show()
    }
}