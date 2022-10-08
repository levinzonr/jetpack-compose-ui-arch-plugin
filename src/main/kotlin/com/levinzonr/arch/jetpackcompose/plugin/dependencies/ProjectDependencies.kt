package com.levinzonr.arch.jetpackcompose.plugin.dependencies

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.levinzonr.arch.jetpackcompose.plugin.TemplateGenerator

class ProjectDependencies(project: Project?) {
    val generator = TemplateGenerator(project!!)
    val editor: FileEditorManager = FileEditorManager.getInstance(project!!)


}