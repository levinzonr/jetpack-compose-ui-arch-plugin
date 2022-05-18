import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.layout.panel
import com.levinzonr.arch.jetpackcompose.plugin.ui.ComposeComponentViewModel
import javax.swing.JComponent

class ComposeComponentDialog(
        private val viewModel: ComposeComponentViewModel
) : DialogWrapper(true) {

    private lateinit var panel: DialogPanel

    init {
        init()
    }

    override fun createCenterPanel(): JComponent {
        panel = panel {
            row { label("New Jetpack Compose UI Component") }
            row { textField(viewModel::name).focused() }
        }

        return panel
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}