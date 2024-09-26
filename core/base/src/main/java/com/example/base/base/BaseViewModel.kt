package com.example.base.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {

    protected val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    protected val _error: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val error: StateFlow<Boolean> get() = _error

    protected val _success: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val success: StateFlow<Boolean> get() = _success

}