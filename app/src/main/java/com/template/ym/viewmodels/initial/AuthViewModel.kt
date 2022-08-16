package com.template.ym.viewmodels.initial

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.core.di.PreferenceProvider
import com.template.core.di.PreferenceProviderImp
import com.template.core.model.account.CoreAccount
import com.template.core.usecase.account.UseCaseAuth
import com.template.ym.MyApp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthViewModel : ViewModel() {

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

    suspend fun auth(email: String, password: String) {
        account = useCaseAuth.invoke(email = email, password = password)
        if (account!=null){
            preferenceProvider.updateToken(account!!.email +"%"+ account!!.password)
        }
    }
}