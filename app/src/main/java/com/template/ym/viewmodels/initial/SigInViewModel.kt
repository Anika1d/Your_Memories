package com.template.ym.viewmodels.initial

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.template.core.di.PreferenceProviderImp
import com.template.core.model.account.CoreAccount
import com.template.core.usecase.account.UseCaseAuth
import com.template.ym.MyApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SigInViewModel : ViewModel() {
    @Inject
    lateinit var preferenceProvider: PreferenceProviderImp

    @Inject
    lateinit var useCaseAuth: UseCaseAuth

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
}