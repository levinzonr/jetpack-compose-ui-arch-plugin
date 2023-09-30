package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.noteComponent
import com.intellij.ui.dsl.builder.*
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import com.levinzonr.arch.jetpackcompose.plugin.core.Links
import com.levinzonr.arch.jetpackcompose.plugin.features.feedback.feedbackActions
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

            row {
                text(
                    "Creates a set of files for the new Feature.<br>" +
                            " All files will be placed in the package with the same name as the feature",
                    maxLineLength = DEFAULT_COMMENT_WIDTH
                )
            }

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
                    BrowserUtil.browse(Links.DOCS)
                }
                feedbackActions()
            }

        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}