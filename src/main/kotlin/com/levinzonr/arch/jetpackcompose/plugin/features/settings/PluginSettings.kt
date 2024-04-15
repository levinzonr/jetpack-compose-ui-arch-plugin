package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
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
                group("Ollama") {
                    row("Host") {
                        textField()
                            .bindText(viewModel::host)

                    }
                    row("Model") {
                        textField()
                            .bindText(viewModel::model)
                    }
                    row {
                        button(
                            text = "Test connection",
                            actionListener = { viewModel.testOllamaConnection() }
                        )
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