package com.template.ym.viewmodels.memories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.core.model.memories.CoreMemories
import com.template.core.usecase.memories.UseCaseGetMemoriesById
import com.template.ym.MyApp
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoMemoriesViewModel : ViewModel() {

    @Inject
    lateinit var useCaseGetMemoriesById: UseCaseGetMemoriesById


    private var memories: CoreMemories? by mutableStateOf(null)

    init {
        MyApp.appComponent.inject(this)
    }


    fun initMemories(id: Long) {
        viewModelScope.launch {
            memories = useCaseGetMemoriesById.invoke(id)
        }
    }

    fun getMemoriess() = memories

}