package com.levinzonr.arch.jetpackcompose.plugin.features.newcomponent

import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.core.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.PluginDependencies
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.Locale.getDefault

class ComposeComponentViewModel(
        private val directory: PsiDirectory,
        private val projectDependencies: ProjectDependencies
) : BaseViewModel(){
    var name: String = ""
        get() = field.replaceFirstChar { if (it.isLowerCase()) it.titlecase(getDefault()) else it.toString() }
    val successFlow = MutableSharedFlow<Unit>()

    fun onOkButtonClick() {
        val settings = PluginDependencies.settings.get()
        val uiLibraryType = settings.uiLibrarySettings.type
        
        val properties: MutableMap<String, Any> = mutableMapOf(
            PropertyKeys.Name to name,
            PropertyKeys.UI_LIBRARY_TYPE to uiLibraryType.name
        )
        val file = projectDependencies.generator.generateKt("ComposeComponent", name, directory, properties)
        projectDependencies.editor.openFile(file.virtualFile, true)
        scope.launch { successFlow.emit(Unit) }
    }
}
