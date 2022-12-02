package com.levinzonr.arch.jetpackcompose.plugin.features.newcomponent

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ComposeComponentDialog(
        private val viewModel: ComposeComponentViewModel
) : BaseDialog() {

    init {
        init()
        viewModel.successFlow
                .onEach { close(0) }
                .launchIn(dialogScope)
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New Jetpack Compose UI Component") }
            row { textField(viewModel::name).focused() }
            noteRow("Creates a new Composable Component and its Preview based on the name given")
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}