package com.levinzonr.arch.jetpackcompose.plugin.ui

import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.layout.panel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.swing.JComponent

class ComposeArchDialog(
    private val viewModel: ComposeArchDialogViewModel
) : DialogWrapper(true) {

    private val scope = CoroutineScope(Dispatchers.Main)
    private lateinit var panel: DialogPanel

    init {
        init()
        viewModel.successFlow
            .onEach { close(0) }
            .launchIn(scope)

    }


    override fun createCenterPanel(): JComponent {
        panel = panel {
            row { label("New Jetpack Compose Feature") }
            row { textField(viewModel::name).focused() }
        }

        return panel
    }

    override fun doOKAction() {
        super.doOKAction()
        panel.apply()
        viewModel.onOkButtonClick()
    }
}