package com.levinzonr.arch.jetpackcompose.plugin.core

import com.intellij.openapi.Disposable
import com.intellij.openapi.observable.properties.ObservableMutableProperty

class ObservableValue<T>(defaultValue: T) : ObservableMutableProperty<T> {
    private var value = defaultValue
    private val listeners = mutableListOf<(T) -> Unit>()

    override fun set(value: T) {
        if (this.value != value) {
            this.value = value
            listeners.forEach { it.invoke(value) }
        }
    }

    override fun afterChange(listener: (T) -> Unit) {
        listeners.add(listener)
    }

    override fun afterChange(listener: (T) -> Unit, parentDisposable: Disposable) {
        listeners.add(listener)
    }

    override fun get(): T {
        return value
    }
}