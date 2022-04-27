package com.levinzonr.jetpackcompose.arch

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import com.levinzonr.jetpackcompose.arch.ui.ComposeArchDialog
import com.levinzonr.jetpackcompose.arch.ui.ComposeArchDialogViewModel

class ComposeArchAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val generator = TemplateGenerator(e.project!!)
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        val viewModel = ComposeArchDialogViewModel(generator, directory)
        ComposeArchDialog(viewModel).show()
    }
}