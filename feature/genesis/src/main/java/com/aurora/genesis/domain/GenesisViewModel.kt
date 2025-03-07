package com.aurora.genesis.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenesisViewModel @Inject constructor() : ViewModel() {
    init {
        Log.w("AAA", "Yay")
    }

    fun start() {
        Log.w("AAA", "Yay 1")
    }
}