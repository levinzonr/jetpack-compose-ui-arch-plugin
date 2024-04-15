package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import com.levinzonr.arch.jetpackcompose.plugin.core.Links
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced.AdvancedDialog
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced.AdvancedViewModel
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.PluginSettings
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
        title = "New Jetpack Compose Feature"
    }

    override fun createPanel(): DialogPanel {
        return panel {
            group {

                row {
                    text(
                        text = "Creates a set of files for the new Feature.<br>" +
                                " All files will be placed in the package with the same name as the feature",
                        maxLineLength = DEFAULT_COMMENT_WIDTH
                    )
                }
                row {
                    textField()
                        .focused()
                        .bindText(viewModel::name)
                        .align(Align.FILL)
                        .comment("Name of the feature<br>i.e Login")
                }

            }

            group("AI Helper - Experimental") {
                row {
                    text(
                        text = "You can use AI buddy of your choice to generate more tailored content.<br>" +
                                "Needs to be configured and enabled first",
                        maxLineLength = DEFAULT_COMMENT_WIDTH
                    )
                    link("⚙\uFE0F Configure") {
                        PluginSettings.open()
                    }
                }
                row {
                    textField()
                        .comment("AI Prompt with requirements<br>i.e: username, password, forgot passwrod button")
                        .bindText(viewModel::description)
                        .align(Align.FILL)
                }


                row {
                    label("Waiting for AI to respond...")
                }.visibleIf(viewModel.aiLoadingState)
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