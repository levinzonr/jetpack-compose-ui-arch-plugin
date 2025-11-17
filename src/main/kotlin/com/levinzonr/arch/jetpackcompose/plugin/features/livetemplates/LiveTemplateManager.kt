package com.levinzonr.arch.jetpackcompose.plugin.features.livetemplates

import com.intellij.codeInsight.template.impl.TemplateSettings
import com.levinzonr.arch.jetpackcompose.plugin.features.ui.UILibraryType

object LiveTemplateManager {
    
    private const val MATERIAL_GROUP = "Compose Material Live Templates"
    private const val MATERIAL3_GROUP = "Compose Material3 Live Templates"
    
    fun updateTemplatesForUILibrary(uiLibraryType: UILibraryType) {
        val templateSettings = TemplateSettings.getInstance()
        
        when (uiLibraryType) {
            UILibraryType.Material -> {
                // Enable Material templates, disable Material3
                enableTemplateGroup(templateSettings, MATERIAL_GROUP)
                disableTemplateGroup(templateSettings, MATERIAL3_GROUP)
            }
            UILibraryType.Material3 -> {
                // Enable Material3 templates, disable Material
                enableTemplateGroup(templateSettings, MATERIAL3_GROUP)
                disableTemplateGroup(templateSettings, MATERIAL_GROUP)
            }
        }
    }
    
    private fun enableTemplateGroup(templateSettings: TemplateSettings, groupName: String) {
        templateSettings.templates.forEach { template ->
            if (template.groupName == groupName) {
                template.isDeactivated = false
            }
        }
    }
    
    private fun disableTemplateGroup(templateSettings: TemplateSettings, groupName: String) {
        templateSettings.templates.forEach { template ->
            if (template.groupName == groupName) {
                template.isDeactivated = true
            }
        }
    }
}
