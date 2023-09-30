package com.levinzonr.arch.jetpackcompose.plugin.features.newcomponent

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.noteComponent
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import com.levinzonr.arch.jetpackcompose.plugin.features.feedback.feedbackActions
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
            row { textField().focused().bindText(viewModel::name).horizontalAlign(HorizontalAlign.FILL) }
            row { comment("Creates a new Composable Component and its Preview based on the name given") }
            row { feedbackActions() }
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}