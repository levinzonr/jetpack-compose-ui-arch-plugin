package com.levinzonr.arch.jetpackcompose.plugin.core

import com.intellij.openapi.Disposable
import com.intellij.openapi.observable.properties.ObservableMutableProperty

class ObservableBoolean(defaultValue: Boolean = false) : ObservableMutableProperty<Boolean> {

    private var value: Boolean = defaultValue
    private val listeners = mutableListOf<(Boolean) -> Unit>()


    override fun set(value: Boolean) {
        this.value = value
        listeners.forEach { it.invoke(value) }
    }

    override fun afterChange(listener: (Boolean) -> Unit) {
        listeners.add(listener)
    }

    override fun afterChange(listener: (Boolean) -> Unit, parentDisposable: Disposable) {
        listener.invoke(value)
    }

    override fun get(): Boolean {
        return value
    }
}