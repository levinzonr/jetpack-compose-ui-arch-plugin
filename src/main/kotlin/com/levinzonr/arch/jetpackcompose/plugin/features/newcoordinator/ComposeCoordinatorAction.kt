package com.levinzonr.arch.jetpackcompose.plugin.features.newcoordinator

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.newcoordinator.ComposeCoordinatorViewModel
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.injection.AdvancedViewModelFactory

class ComposeCoordinatorAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val data = e.getData(CommonDataKeys.PSI_ELEMENT)

        val directory = when (data) {
            is PsiDirectory -> data
            is PsiFile -> data.parent
            else -> null
        } ?: return

        val dependencies = ProjectDependencies(e.project)
        val viewModel = ComposeCoordinatorViewModel(directory, dependencies)
        val advancedViewModel = AdvancedViewModelFactory.create(dependencies)
        ComposeCoordinatorDialog(viewModel, advancedViewModel).show()
    }
}
