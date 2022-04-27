package com.levinzonr.jetpackcompose.arch

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBTextField
import com.intellij.ui.layout.CellBuilder
import com.intellij.ui.layout.Row
import com.intellij.ui.layout.panel
import javax.swing.JComponent
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

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

    private fun Row.textField(onChange: (String) -> Unit): CellBuilder<JBTextField> {
        return textField({ "" }, { }).also {
            it.component.let { component ->
                component.document.addDocumentListener(object : DocumentListener {
                    override fun insertUpdate(e: DocumentEvent?) {
                        onChange.invoke(component.text)
                    }

                    override fun removeUpdate(e: DocumentEvent?) {
                        onChange.invoke(component.text)

                    }

                    override fun changedUpdate(e: DocumentEvent?) {
                        onChange.invoke(component.text) }
                })
            }
        }
    }


    fun show(onOk: (String) -> Unit) {
        this.onOk = onOk
        show()
    }
}