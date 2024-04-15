package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.injection

import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.PluginDependencies
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.FeatureBreakdownRepository
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.ComposeArchDialogViewModel
import com.levinzonr.arch.jetpackcompose.plugin.features.ollama.OllamaGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.data.SettingsRepositoryImpl

object ComposeArchDialogViewModelFactory {



    fun create(psiDirectory: PsiDirectory, dependencies: ProjectDependencies) : ComposeArchDialogViewModel {
        val settings = SettingsRepositoryImpl(PluginDependencies.preferences)
        return ComposeArchDialogViewModel(
            directory = psiDirectory,
            generator = dependencies.generator,
            repository = ExperimentalFeaturesRepositoryFactory.create(dependencies),
            editorManager = dependencies.editor,
            application = dependencies.application,
            featureBreakdownRepository = FeatureBreakdownRepository(OllamaGenerator(settings.get().ollama))
        )
    }
}