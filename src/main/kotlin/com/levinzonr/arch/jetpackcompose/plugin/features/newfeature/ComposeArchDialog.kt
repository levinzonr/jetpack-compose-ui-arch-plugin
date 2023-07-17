package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.noteComponent
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
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
            row {
                textField()
                    .focused()
                    .bindText(viewModel::name)
                    .horizontalAlign(com.intellij.ui.dsl.gridLayout.HorizontalAlign.FILL)
            }

            noteComponent(
                "Creates a set of files for the new Feature.\n" +
                        " All files will be placed in the package with the same name as the feature"
            )
            group("Options") {
                row {
                    checkBox("Also create package for the feature")
                        .bindSelected(viewModel::createFeaturePackage)

                }

                row {
                    checkBox("Use collectAsStateWithLifecycle")
                        .bindSelected(viewModel::flowWithLifecycleEnabled)
                }
                row { comment("Collect the flow in the Route component in a lifecycle aware way") }
            }

            row {
                link("ℹ\uFE0F Learn more") {
                    BrowserUtil.browse("https://levinzonr.github.io/compose-ui-arch-docs")
                }
            }

        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}