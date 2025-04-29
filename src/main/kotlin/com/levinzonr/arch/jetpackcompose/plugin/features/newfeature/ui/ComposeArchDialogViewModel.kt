package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui

import com.intellij.openapi.application.*
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.core.*
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.FeatureBreakdownGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.FeatureBreakdown
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.repository.FeatureConfigurationRepository
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.models.FeatureProperties
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.SettingsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.Locale
import java.util.Locale.getDefault

class ComposeArchDialogViewModel(
    private val directory: PsiDirectory,
    private val generator: TemplateGenerator,
    private val repository: FeatureConfigurationRepository,
    private val editorManager: FileEditorManager,
    private val application: Application,
    private val featureBreakdownGenerator: FeatureBreakdownGenerator,
    private val settingsRepository: SettingsRepository
) : BaseViewModel() {

    var name: String = ""
        get() = field.replaceFirstChar { if (it.isLowerCase()) it.titlecase(getDefault()) else it.toString() }

    val successFlow = MutableSharedFlow<Unit>()
    val errorFlow = MutableSharedFlow<String>()
    val loadingFlow = MutableSharedFlow<Boolean>()

    var description: String = ""

    var createFeaturePackage: Boolean = true
    var createNavigationCode: Boolean = false

    fun onOkButtonClick() {
        scope.launch {
            if (description.isNotBlank()) {
                loadingFlow.emit(true)
                featureBreakdownGenerator.generate(name, description)
                    .onFailure { errorFlow.emit(it.message ?: "Unkown API Error") }
                    .onSuccess { breakdown ->
                        generateFiles(breakdown)
                    }
                loadingFlow.emit(false)
            } else {
                generateFiles(null)
            }
        }
    }

    private fun generateFiles(breakdown: FeatureBreakdown?) {
        val config = repository.get()
        val properties = FeatureProperties(
            name, config, breakdown, createNavigationCode).toProperties()
        invokeLater(ModalityState.defaultModalityState()) {
            runWriteAction {
                val featPackage =
                    if (createFeaturePackage) directory.createSubdirectory(name.lowercase()) else directory
                val file = generator.generateKt("ComposeContract", "${name}Contract", featPackage, properties)
                generator.generateKt("ComposeScreen", "${name}Screen", featPackage, properties)
                generator.generateKt("ComposeViewModel", "${name}ViewModel", featPackage, properties)
                generator.generateKt("ComposeCoordinator", "${name}Coordinator", featPackage, properties)
                generator.generateKt("ComposeRoute", "${name}Route", featPackage, properties)

                if (featPackage.findSubdirectory("components") == null) {
                    featPackage.createSubdirectory("components")
                }

                editorManager.openFile(file.virtualFile, true)
            }
            scope.launch { successFlow.emit(Unit) }
        }

    }

}