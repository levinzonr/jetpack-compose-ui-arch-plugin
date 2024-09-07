package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.DEFAULT_COMMENT_WIDTH
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import com.levinzonr.arch.jetpackcompose.plugin.features.feedback.feedbackActions

class AdvancedDialog(
    private val viewModel: AdvancedViewModel
) : BaseDialog() {

    init {
        init()
    }

    override fun createPanel(): DialogPanel {
        return panel {

            row {
                rowComment("Configure advanced settings for the feature" +
                        "settings will be persisted for the current project.")
            }

            group("State Collection") {
                row {
                    checkBox("Use collectAsStateWithLifecycle")
                        .bindSelected(viewModel::useCollectFlowWithLifecycle)
                }
                row { comment("Collect the flow in the Route component in a lifecycle aware way") }
            }
            group("Preview Parameter") {
                row {
                    checkBox("Use PreviewParameterProvider")
                        .bindSelected(viewModel::usePreviewParameterProvider)
                }
                row { comment("Also generate a PreviewParameterProvider to preview various States in a Screen component.") }
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

            group("Navigation Type") {
                buttonsGroup {
                    row {
                        radioButton("Kiwi Compose Navigation Typed")
                            .bindSelected(viewModel::kiwiSetter)
                    }

                    row {
                        link("https://github.com/kiwicom/navigation-compose-typed") {
                            BrowserUtil.open("https://github.com/kiwicom/navigation-compose-typed")
                        }
                    }

                    row {
                        radioButton("Compose Destinations")
                            .bindSelected(viewModel::composeDestinationsSetter)
                    }

                    row {
                        link("https://github.com/raamcosta/compose-destinations") {
                            BrowserUtil.open("https://github.com/raamcosta/compose-destinations")
                        }
                    }

                }
            }

            group("Actions Provider") {
                buttonsGroup {

                    row {
                        radioButton("Data Class")
                            .bindSelected(viewModel::dataClassActionsSetter)
                    }

                    row {
                        comment("All actions will be generated as data classes (default)")
                    }

                    row {
                        radioButton("Sealed Interface")
                            .bindSelected(viewModel::sealedActionsSetter)
                    }

                    row {
                        comment("Actions will be generated as a sealed interface i.e LoginAction.UsernameChange")
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