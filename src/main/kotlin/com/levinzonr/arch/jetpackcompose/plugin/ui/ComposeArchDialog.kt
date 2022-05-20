package com.levinzonr.arch.jetpackcompose.plugin.ui

import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.layout.panel
import com.levinzonr.arch.jetpackcompose.plugin.base.BaseDialog
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.Dependencies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.swing.JComponent

class ComposeArchDialog(
        private val viewModel: ComposeArchDialogViewModel,
) : BaseDialog() {

    init {
        init()
        viewModel
                .successFlow
                .onEach { close(0) }
                .launchIn(dialogScope)
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New Jetpack Compose Feature") }
            row { textField(viewModel::name).focused() }
            noteRow("Creates a set of files for the new Feature.\n" +
                    " All files will be placed in the package with the same name as the feature")
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}