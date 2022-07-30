package com.template.ym.viewmodels.memories

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.core.di.PreferenceProviderImp
import com.template.core.model.account.CoreAccount
import com.template.core.model.memories.CoreMemories
import com.template.core.model.memories.EnterMemories
import com.template.core.usecase.account.UseCaseAuth
import com.template.core.usecase.memories.UseCaseCreateMemories
import com.template.core.usecase.memories.UseCaseGetMemoriesById
import com.template.core.usecase.memories.UseCaseGetMemoriesByOwner
import com.template.core.usecase.memories.UseCaseUpdateMemories
import com.template.ym.MyApp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ChangeOrCreateMemoriesViewModel : ViewModel() {
    @Inject
    lateinit var useCaseCreateMemories: UseCaseCreateMemories

    @Inject
    lateinit var useCaseGetMemoriesById: UseCaseGetMemoriesById

    @Inject
    lateinit var preferenceProvider: PreferenceProviderImp

    @Inject
    lateinit var useCaseAuth: UseCaseAuth


    @Inject
    lateinit var useCaseUpdateMemories: UseCaseUpdateMemories
    var memories: CoreMemories? by mutableStateOf(null)
    var account: CoreAccount? by mutableStateOf(null)


    init {
        MyApp.appComponent.inject(this)

        runBlocking {
            account = useCaseAuth.invoke(
                email = preferenceProvider.token!!.split("%")[0],
                password = preferenceProvider.token!!.split("%")[1]
            )
        }
    }

    fun createMemories(enterMemories: EnterMemories) {
        viewModelScope.launch {
            useCaseCreateMemories.invoke(enterMemories)
        }
    }

    suspend fun initMemories(id: Long) {

        memories = useCaseGetMemoriesById.invoke(id)
    }


    fun updateMemories(coreMemories: CoreMemories) {
        viewModelScope.launch {
            useCaseUpdateMemories.invoke(coreMemories)
        }
    }
}