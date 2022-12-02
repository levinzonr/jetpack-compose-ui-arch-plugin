package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
            row { checkBox("Also create package for the feature", viewModel::createFeaturePackage) }

            noteRow("Creates a set of files for the new Feature.\n" +
                    " All files will be placed in the package with the same name as the feature")

            hideableRow("Experimental") {
                checkBox(
                    text = "Use collectAsStateWithLifecycle",
                    prop = viewModel::flowWithLifecycleEnabled,
                    comment = buildString {
                        appendLine("Collect the flow in the Route component in a lifecycle aware way")
                        append("Requires opt-in")
                    })
            }

        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}