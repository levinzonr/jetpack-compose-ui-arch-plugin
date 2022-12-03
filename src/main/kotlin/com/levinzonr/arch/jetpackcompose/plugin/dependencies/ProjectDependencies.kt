package com.levinzonr.arch.jetpackcompose.plugin.dependencies

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.levinzonr.arch.jetpackcompose.plugin.core.TemplateGenerator

class ProjectDependencies(val project: Project?) {
    val generator = TemplateGenerator(project!!)
    val editor: FileEditorManager = FileEditorManager.getInstance(project!!)
    val properties: PropertiesComponent = PropertiesComponent.getInstance()
}