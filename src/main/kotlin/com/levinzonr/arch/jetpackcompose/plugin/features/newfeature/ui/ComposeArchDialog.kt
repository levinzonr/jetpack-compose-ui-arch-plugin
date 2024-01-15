package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import com.levinzonr.arch.jetpackcompose.plugin.core.Links
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced.AdvancedDialog
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced.AdvancedViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ComposeArchDialog(
    private val viewModel: ComposeArchDialogViewModel,
    private val advancedViewModel: AdvancedViewModel
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
                    .align(Align.FILL)
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
                    link("More options") {
                        AdvancedDialog(advancedViewModel).show()
                    }
                }
            }

            row {
                link("ℹ\uFE0F Learn more") {
                    BrowserUtil.browse(Links.DOCS)
                }
            }

        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}