package com.levinzonr.arch.jetpackcompose.plugin.features.newcoordinator

import com.intellij.openapi.application.*
import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.core.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.repository.FeatureConfigurationRepository
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.injection.ExperimentalFeaturesRepositoryFactory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.Locale.getDefault

class ComposeCoordinatorViewModel(
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : BaseViewModel() {
    
    private val repository: FeatureConfigurationRepository = 
        ExperimentalFeaturesRepositoryFactory.create(projectDependencies)
    
    var name: String = ""
        get() = field.replaceFirstChar { 
            if (it.isLowerCase()) it.titlecase(getDefault()) else it.toString() 
        }
    
    val successFlow = MutableSharedFlow<Unit>()

    fun onOkButtonClick() {
        val config = repository.get()
        // Create properties for coordinator generation
        val properties = mutableMapOf<String, Any>().apply {
            put(PropertyKeys.Name, name)
            put(PropertyKeys.PackageName, directory.getPackageName() ?: "")
            put(PropertyKeys.VIEW_MODEL_INJECTION, config.injection.name)
        }
        
        invokeLater(ModalityState.defaultModalityState()) {
            runWriteAction {
                val file = projectDependencies.generator.generateKt(
                    "ComposeCoordinator", 
                    "${name}Coordinator", 
                    directory, 
                    properties
                )
                projectDependencies.editor.openFile(file.virtualFile, true)
            }
            scope.launch { successFlow.emit(Unit) }
        }
    }
    
    private fun PsiDirectory.getPackageName(): String? {
        return com.intellij.psi.impl.file.PsiDirectoryFactory
            .getInstance(projectDependencies.project)
            .getQualifiedName(this, false)
    }
}
