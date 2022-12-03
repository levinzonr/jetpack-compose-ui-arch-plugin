package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.injection

import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ComposeArchDialogViewModel

object ComposeArchDialogViewModelFactory {

    fun create(psiDirectory: PsiDirectory, dependencies: ProjectDependencies) : ComposeArchDialogViewModel {
        return ComposeArchDialogViewModel(
            directory = psiDirectory,
            generator = dependencies.generator,
            repository = ExperimentalFeaturesRepositoryFactory.create(dependencies),
            editorManager = dependencies.editor
        )
    }
}