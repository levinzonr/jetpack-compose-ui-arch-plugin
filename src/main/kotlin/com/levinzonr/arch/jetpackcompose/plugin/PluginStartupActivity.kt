package com.levinzonr.arch.jetpackcompose.plugin

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.PluginDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.livetemplates.LiveTemplateManager

class PluginStartupActivity : StartupActivity {


    override fun runActivity(project: Project) {
        try {
            val settings = PluginDependencies.settings.get()
            LiveTemplateManager.updateTemplatesForUILibrary(settings.uiLibrarySettings.type)
        } catch (e: Exception) {
            thisLogger().warn("Failed to initialize live templates on startup", e)
        }
    }
}