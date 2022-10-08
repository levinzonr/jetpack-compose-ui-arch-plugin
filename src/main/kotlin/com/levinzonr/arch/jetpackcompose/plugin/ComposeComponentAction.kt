package com.levinzonr.arch.jetpackcompose.plugin

import ComposeComponentDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.ui.ComposeArchDialog
import com.levinzonr.arch.jetpackcompose.plugin.ui.ComposeArchDialogViewModel
import com.levinzonr.arch.jetpackcompose.plugin.ui.ComposeComponentViewModel

class ComposeComponentAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        val viewModel = ComposeComponentViewModel(directory, ProjectDependencies(e.project))
        ComposeComponentDialog(viewModel).show()
    }
}