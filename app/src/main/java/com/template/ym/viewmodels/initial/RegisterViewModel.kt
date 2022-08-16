package com.template.ym.viewmodels.initial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.core.model.account.CoreAccount
import com.template.core.model.account.EnterAccount
import com.template.core.usecase.account.UseCaseCreateAccount
import com.template.core.usecase.account.UseCaseGetAccountByEmail
import com.template.ym.MyApp
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel() : ViewModel() {

    @Inject
    lateinit var useCaseCreateAccount: UseCaseCreateAccount

    @Inject
    lateinit var useCaseGetAccountByEmail: UseCaseGetAccountByEmail

    init {
        MyApp.appComponent.inject(this)
    }

    fun createAccount(enterAccount: EnterAccount) {
        viewModelScope.launch {
            useCaseCreateAccount.invoke(enterAccount)
        }
    }


    fun isCreatedAccount(email: String): CoreAccount? {
        var tmp: CoreAccount? = null
        viewModelScope.launch {
            tmp = useCaseGetAccountByEmail.invoke(email)
        }
        return tmp
    }
}