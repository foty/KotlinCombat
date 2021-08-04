package com.example.upacket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Create by lxx
 * Date : 2020/12/22 17:07
 * Use by
 */


fun BaseViewModel.asyncRequest(block: suspend () -> Unit) {
    viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {

        }.onFailure {

        }
    }
}