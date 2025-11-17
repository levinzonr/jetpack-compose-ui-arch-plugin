package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.listCellRenderer
import com.levinzonr.arch.jetpackcompose.plugin.features.livetemplates.LiveTemplateManager
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.injection.SettingsViewModelFactory
import com.levinzonr.arch.jetpackcompose.plugin.features.ui.UILibraryType
import javax.swing.JComponent

class UISettingsConfigurable : Configurable {

    private val viewModel: UISettingsViewModel = SettingsViewModelFactory.createUI()
    private lateinit var dialogPanel: DialogPanel

    init {
        // Initialize templates based on current settings
        LiveTemplateManager.updateTemplatesForUILibrary(viewModel.uiLibraryType)
    }

    override fun createComponent(): JComponent? {
        dialogPanel = panel {
            row {
                text("Configure UI-related settings for generated templates")
            }

            row("UI Library") {
                comboBox(UILibraryType.entries, listCellRenderer { value, index, selected ->
                    text = value.toString()
                }).bindItem(viewModel::uiLibraryType)

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
        return "UI Settings"
    }
}
