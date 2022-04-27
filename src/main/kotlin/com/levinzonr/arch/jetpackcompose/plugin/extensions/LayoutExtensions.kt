package com.levinzonr.arch.jetpackcompose.plugin.extensions

import com.intellij.ui.components.JBTextField
import com.intellij.ui.layout.CellBuilder
import com.intellij.ui.layout.Row
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

internal fun Row.textField(onChange: (String) -> Unit): CellBuilder<JBTextField> {
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