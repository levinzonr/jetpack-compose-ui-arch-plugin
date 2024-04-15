package com.levinzonr.arch.jetpackcompose.plugin.dependencies

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.swing.Swing
import kotlinx.serialization.json.Json

object Dependencies {
    val ioScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Swing)
    val ioDispatcher = Dispatchers.IO
    val json = Json {
        ignoreUnknownKeys = false
        isLenient = false
    }
}