package com.template.ym.viewmodels.memories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.core.model.memories.CoreMemories
import com.template.core.usecase.memories.UseCaseGetMemoriesByOwner
import com.template.ym.MyApp
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListMemoriesViewModel : ViewModel() {
    @Inject
    lateinit var useCaseGetMemoriesByOwner: UseCaseGetMemoriesByOwner

    var listMemories by mutableStateOf(
        emptyList<CoreMemories>()
    )

    init {
        MyApp.appComponent.inject(this)
    }

    fun initMemories(id: Long) {
        viewModelScope.launch { listMemories = useCaseGetMemoriesByOwner.invoke(id) }
    }

    fun getListMemoriesByOwner() = listMemories
}