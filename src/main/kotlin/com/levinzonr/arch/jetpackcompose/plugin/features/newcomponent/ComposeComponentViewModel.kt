package com.levinzonr.arch.jetpackcompose.plugin.features.newcomponent

import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.core.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ComposeComponentViewModel(
        private val directory: PsiDirectory,
        private val projectDependencies: ProjectDependencies
) : BaseViewModel(){
    var name: String = ""
        get() = field.capitalize()
    val successFlow = MutableSharedFlow<Unit>()

    fun onOkButtonClick() {
        val properties: MutableMap<String, Any> = mutableMapOf(PropertyKeys.Name to name)
        val file = projectDependencies.generator.generateKt("ComposeComponent", name, directory, properties)
        projectDependencies.editor.openFile(file.virtualFile, true)
        scope.launch { successFlow.emit(Unit) }
    }
}
