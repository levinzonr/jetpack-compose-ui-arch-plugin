package com.levinzonr.arch.jetpackcompose.plugin

import ComposeComponentDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.ui.ComposeArchDialog
import com.levinzonr.arch.jetpackcompose.plugin.ui.ComposeArchDialogViewModel
import com.levinzonr.arch.jetpackcompose.plugin.ui.ComposeComponentViewModel

class ComposeComponentAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val generator = TemplateGenerator(e.project!!)
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        val viewModel = ComposeComponentViewModel(directory, generator)
        ComposeComponentDialog(viewModel).show()
    }
}