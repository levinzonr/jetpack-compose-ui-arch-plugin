package com.levinzonr.arch.jetpackcompose.plugin.core

import com.levinzonr.arch.jetpackcompose.plugin.dependencies.Dependencies

abstract class BaseViewModel() {
    protected val scope = Dependencies.ioScope
}