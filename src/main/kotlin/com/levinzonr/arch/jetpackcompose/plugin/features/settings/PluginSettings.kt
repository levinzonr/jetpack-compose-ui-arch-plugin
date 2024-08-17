package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.injection.SettingsViewModelFactory
import javax.swing.JComponent

class PluginSettings : Configurable {

    private val viewModel: SettingsViewModel = SettingsViewModelFactory.create()
    private lateinit var dialogPanel: DialogPanel

    override fun createComponent(): JComponent? {
        dialogPanel =  panel {
            group("AI Settings") {
                row {
                    text("Configure the AI settings for the plugin and manage the AI models, API keys, and other settings")
                }
                group("\uD83E\uDD99 Ollama") {
                    row {
                        text(
                            text = "Ollama is an advanced AI tool that allows users to easily set up and run large " +
                                "language models locally<br>" +
                            "<br>In order to use Ollama, you need to have the Ollama CLI installed on your machine. codegemma model is recommended",
                            maxLineLength = DEFAULT_COMMENT_WIDTH
                        )

                        link("Install Ollama") {
                            BrowserUtil.open("https://ollama.ai/")
                        }
                    }

                    row("Host") {
                        textField()
                            .bindText(viewModel::host)

                    }
                    row("Model") {
                        textField()
                            .bindText(viewModel::model)
                    }

                    row("Timeout in seconds") {
                        intTextField()
                            .bindIntText(viewModel::timeoutSeconds)
                    }

                    row {
                        button(
                            text = "Test connection (needs to applied first)",
                        ) {
                            viewModel.testOllamaConnection()
                        }
                        text(text = "")
                            .bindText(viewModel.ollamaConnectionStatus)
                    }



                }
            }

            group("Navigation") {
                row {
                    text("Configure the navigation settings for the plugin")
                }

                row("Navigation Type") {
                    radioButton("Kiwi")
                        .bindSelected(viewModel::kiwiEnabled)

                    radioButton("Compose Destinations")
                        .bindSelected(viewModel::composeDestinationsEnabled)
                }

                row("Navigation Name Suffix") {
                    textField()
                        .bindText(viewModel::navSuffix)

                    text("fx: Home${viewModel.navSuffix}")
                }
            }
        }

        return dialogPanel
    }

    override fun isModified(): Boolean {
        return dialogPanel.isModified()
    }

    override fun apply() {
        dialogPanel.apply()
        viewModel.apply()
    }

    override fun reset() {
        viewModel.reset()
    }

    override fun disposeUIResources() {}

    override fun getDisplayName(): String {
       return "Jetpack Compose UI Architecture Plugin Settings"
    }

    companion object {
        fun open(
            project: Project = ProjectManager.getInstance().defaultProject
        ) {
            ShowSettingsUtil.getInstance().showSettingsDialog(
                project,
                PluginSettings::class.java
            )
        }
    }
}