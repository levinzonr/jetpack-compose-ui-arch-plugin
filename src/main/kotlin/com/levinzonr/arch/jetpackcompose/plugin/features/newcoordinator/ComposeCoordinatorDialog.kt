package com.levinzonr.arch.jetpackcompose.plugin.features.newcoordinator

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import com.levinzonr.arch.jetpackcompose.plugin.features.feedback.feedbackActions
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced.AdvancedDialog
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced.AdvancedViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ComposeCoordinatorDialog(
    private val viewModel: ComposeCoordinatorViewModel,
    private val advancedViewModel: AdvancedViewModel
) : BaseDialog() {

    init {
        init()
        viewModel.successFlow
            .onEach { close(0) }
            .launchIn(dialogScope)
        title = "New Coordinator"
    }

    override fun createPanel(): DialogPanel {
        return panel {
            group {
                row {
                    textField()
                        .focused()
                        .bindText(viewModel::name)
                        .align(Align.FILL)
                        .comment("Name of the coordinator (e.g., Login). The generated file will be named {Name}Coordinator")

                    link("More options") {
                        AdvancedDialog(advancedViewModel).show()
                    }
                }

            }

            row {
                feedbackActions()
            }
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}
