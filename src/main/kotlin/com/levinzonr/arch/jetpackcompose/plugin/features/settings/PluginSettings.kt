package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.intellij.ide.BrowserUtil
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*
import com.levinzonr.arch.jetpackcompose.plugin.core.Links
import javax.swing.JComponent

class PluginSettings : Configurable {

    private lateinit var dialogPanel: DialogPanel

    override fun createComponent(): JComponent? {
        val pluginId = PluginId.getId("com.levinzonr.arch.jetpackcompose.plugin")
        val plugin = PluginManagerCore.getPlugin(pluginId)
        val pluginVersion = plugin?.version ?: "Unknown"
        
        dialogPanel = panel {
            group("About") {
                row {
                    text("Jetpack Compose UI Architecture Plugin")
                }
                row {
                    text("Version: <b>$pluginVersion</b>")
                }
                row {
                    link("⭐ Star on GitHub") {
                        BrowserUtil.open(Links.GITHUB_STAR)
                    }
                    link("📖 Documentation") {
                        BrowserUtil.open(Links.DOCS)
                    }
                    link("🐛 Report Bug") {
                        BrowserUtil.open(Links.BUG_REPORT)
                    }
                    link("💡 Feature Request") {
                        BrowserUtil.open(Links.FEATURE_REQUEST)
                    }
                }
            }
            
            group("Settings") {
                row {
                    text("Configure plugin settings using the subsections below:")
                }
                row {
                    link("⚙️ AI Settings") {
                        ShowSettingsUtil.getInstance().showSettingsDialog(
                            ProjectManager.getInstance().defaultProject,
                            AISettingsConfigurable::class.java
                        )
                    }.comment("Configure the AI settings for the plugin and manage the AI models, API keys, and other settings")
                }
                row {
                    link("🎨 UI Settings") {
                        ShowSettingsUtil.getInstance().showSettingsDialog(
                            ProjectManager.getInstance().defaultProject,
                            UISettingsConfigurable::class.java
                        )
                    }.comment("Configure UI-related settings for generated templates")
                }
            }
        }

        return dialogPanel
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun apply() {
        // Parent configurable - children handle their own apply logic
    }

    override fun reset() {
        // Parent configurable - children handle their own reset logic
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