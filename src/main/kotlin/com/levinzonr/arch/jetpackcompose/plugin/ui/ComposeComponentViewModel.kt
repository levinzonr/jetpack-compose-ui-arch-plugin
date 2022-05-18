package com.levinzonr.arch.jetpackcompose.plugin.ui

import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.TemplateGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ComposeComponentViewModel(
        private val directory: PsiDirectory,
        private val generator: TemplateGenerator
) {
    var name: String = ""
    val successFlow = MutableSharedFlow<Unit>()
    private val scope = CoroutineScope(Dispatchers.Default)

    fun onOkButtonClick() {
        scope.launch { successFlow.emit(Unit) }
    }
}
