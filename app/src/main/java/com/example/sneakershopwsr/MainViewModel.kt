package com.example.sneakershopwsr

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.auth.domain.AuthInfo
import com.example.sneakershopwsr.core.domain.GlobalSessionKeys
import com.example.sneakershopwsr.core.domain.SessionStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val sessionStorage: SessionStorage<AuthInfo>,
): ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                isLogged = sessionStorage.get(GlobalSessionKeys.IS_LOGINED) !== null,
                isFirstEntrance = sessionStorage.get(KEY_IS_FIRST_ENTRANCE) === null
            )
            sessionStorage.set(KEY_IS_FIRST_ENTRANCE, AuthInfo(""))
        }
    }

    companion object {
        private const val KEY_IS_FIRST_ENTRANCE = "KEY_IS_FIRST_ENTRANCE"
    }
}
