package com.template.ym.viewmodels.profile

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.core.di.PreferenceProviderImp
import com.template.core.model.account.CoreAccount
import com.template.core.model.memories.CoreMemories
import com.template.core.usecase.account.UseCaseAuth
import com.template.core.usecase.memories.UseCaseGetMemoriesByOwner
import com.template.ym.MyApp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ProfileViewModel : ViewModel() {
    @Inject
    lateinit var useCaseGetMemoriesByOwner: UseCaseGetMemoriesByOwner

    var listMemories by mutableStateOf(
        emptyList<CoreMemories>()
    )
    @Inject
    lateinit var useCaseAuth: UseCaseAuth

    @Inject
    lateinit var preferenceProvider: PreferenceProviderImp
    var account: CoreAccount? by mutableStateOf(null)
    init {
        MyApp.appComponent.inject(this)
        runBlocking {
            if (preferenceProvider.token != null)
                account = useCaseAuth.invoke(
                    email = preferenceProvider.token!!.split("%")[0],
                    password = preferenceProvider.token!!.split("%")[1]
                )
        }
    }

    fun initMemories(id: Long) {
        viewModelScope.launch { listMemories = useCaseGetMemoriesByOwner.invoke(id) }
    }

    fun getListMemoriesByOwner() = listMemories
    fun exit() {
        preferenceProvider.deleteToken()
    }
}