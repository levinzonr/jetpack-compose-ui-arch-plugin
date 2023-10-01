package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.DEFAULT_COMMENT_WIDTH
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import com.levinzonr.arch.jetpackcompose.plugin.features.feedback.feedbackActions
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.InjectionConfiguration

class AdvancedDialog(
    private val viewModel: AdvancedViewModel
) : BaseDialog() {

    init {
        init()
    }

    override fun createPanel(): DialogPanel {
        return panel {
            group("State Collection") {
                row {
                    checkBox("Use collectAsStateWithLifecycle")
                        .bindSelected(viewModel::useCollectFlowWithLifecycle)
                }
                row { comment("Collect the flow in the Route component in a lifecycle aware way") }
            }
            group("ViewModel Injection") {
                buttonsGroup {
                    row {
                        radioButton("Hilt")
                            .bindSelected(viewModel::hiltSetter)
                    }
                    row {
                        radioButton("Koin")
                            .bindSelected(viewModel::koinSetter)
                    }
                }
            }

            group {
                row {
                    text(
                        text = "Missing some configuration to make your life easier? Feel free to submit a feature request!",
                        maxLineLength = DEFAULT_COMMENT_WIDTH
                    )
                }
                row { feedbackActions() }
            }
        }
    }

    override fun doOKAction() {
        panel.apply()
        close(0)
    }
}