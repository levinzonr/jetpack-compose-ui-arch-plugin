package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.listCellRenderer
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.AIClientType
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
                row("AI Client") {
                    comboBox(AIClientType.entries, listCellRenderer { value, index, selected ->
                        text = value.toString()
                    }).bindItem(viewModel::clientType)
                }

                // Ollama group - shown when Ollama is selected
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

                // OpenAI group - shown when OpenAI is selected
                group("🤖 OpenAI") {

                    row {
                        text(
                            text = "OpenAI provides powerful AI models through their API. You need an API key to use OpenAI services.<br>" +
                            "<br>Get your API key from the OpenAI platform.",
                            maxLineLength = DEFAULT_COMMENT_WIDTH
                        )

                        link("Get API Key") {
                            BrowserUtil.open("https://platform.openai.com/api-keys")
                        }
                    }

                    row("API Key") {
                        passwordField()
                            .bindText(viewModel::openaiApiKey)
                            .comment("API Key Never leaves your IDE")
                            .align(Align.FILL)

                    }

                    row("Host") {
                        textField()
                            .bindText(viewModel::openaiHost)
                    }

                    row("Model ID") {
                        textField()
                            .bindText(viewModel::openaiModelId)
                    }

                    row("Timeout in seconds") {
                        intTextField()
                            .bindIntText(viewModel::openaiTimeoutSeconds)
                    }

                    row {
                        button(
                            text = "Test connection (needs to applied first)",
                        ) {
                            viewModel.testOpenAIConnection()
                        }
                        text(text = "")
                            .bindText(viewModel.openaiConnectionStatus)
                    }
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