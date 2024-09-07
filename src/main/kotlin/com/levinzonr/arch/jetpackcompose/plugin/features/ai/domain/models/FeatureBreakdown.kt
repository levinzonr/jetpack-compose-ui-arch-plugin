package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class FeatureBreakdown(
    val properties: List<StateProperties>,
    val actions: List<Action>
) {

    val propertyStatements = properties.joinToString("\n") {
        val defaultValue = if (it.defaultValue == "") "\"\"" else it.defaultValue
        "val ${it.name}: ${it.type} = ${defaultValue},"
    }

    val actionStatements = actions.joinToString("\n") {
        "val ${it.name}: (${it.typedParams}) -> Unit = {},"
    }

    val actionHandlers = actions.joinToString("\n") {
        "${it.name} = coordinator::handle${it.name.drop(2)},"
    }

    val coordinatorActions = actions.joinToString("\n") {

        if (it.type != Action.Type.Other) {
            val params = it.params.joinToString(", ") { it.name }
            """
        fun handle${it.name.drop(2)}(${it.namedParams}) {
            viewModel.${it.imperativeName}($params)
        }
       """
        } else {
            """
        fun handle${it.name.drop(2)}() {
            // handle ${it.imperativeName} action
        }
            """
        }
    }


    fun actionsHandlers(baseName: String): String {
        return """
                ${
            actions.joinToString("\n") {
                if (it.params.isEmpty()) {
                    "data object ${it.name.drop(2)} : ${baseName}Action"
                } else {
                    "data class ${it.name.drop(2)}(${
                        it.params.joinToString(",\n") {
                            "val ${it.name}: ${it.type}"
                        }
                    }) : ${baseName}Action"
                }
            }
        }
        """.trimIndent()
    }

    fun coordinatorHandlers(baseName: String): String {
        return """
           fun handle(action: ${baseName}Action) {
                when(action) {
                    ${
            actions.joinToString("\n") {
                val imperativeHandler = if (it.type != Action.Type.Other) {
                    "viewModel.${it.imperativeName}(${it.params.joinToString(","){ 
                        "action.${it.name}"
                    }})"
                } else {
                    "// handle ${it.imperativeName} action"
                }
                if (it.params.isEmpty()) {

                    "${baseName}Action.${it.name.drop(2)} -> {\n $imperativeHandler \n}"
                } else {
                    "is ${baseName}Action.${it.name.drop(2)} -> {\n $imperativeHandler \n}"
                }
            }
        }
                }
            }
            
            
        """.trimIndent()
    }


    val viewModelActions = actions.filter { it.type != Action.Type.Other }.joinToString("\n") {
        val params = it.namedParams
        val param = it.params.firstOrNull()?.name
        if (it.type == Action.Type.StateChange && param != null) {
            """
                fun ${it.imperativeName}($params) { 
                    _stateFlow.update { state -> 
                        state.copy($param = $param)
                    }
                }
            """.trimIndent()
        } else
            """
          fun ${it.imperativeName}($params) {
          }
        """.trimIndent()
    }


}

