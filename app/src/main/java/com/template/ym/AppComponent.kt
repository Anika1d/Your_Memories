package com.template.ym

import com.template.core.dagger.AccountModule
import com.template.core.dagger.MainModule
import com.template.core.dagger.MemoriesModule
import com.template.ym.viewmodels.initial.AuthViewModel
import com.template.ym.viewmodels.initial.RegisterViewModel
import com.template.ym.viewmodels.initial.SigInViewModel
import com.template.ym.viewmodels.memories.ChangeOrCreateMemoriesViewModel
import com.template.ym.viewmodels.memories.InfoMemoriesViewModel
import com.template.ym.viewmodels.memories.ListMemoriesViewModel
import com.template.ym.viewmodels.profile.ProfileViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MainModule::class, AccountModule::class, MemoriesModule::class]
)
interface AppComponent {
    fun inject(registerViewModel: RegisterViewModel)
    fun inject(sigInViewModel: SigInViewModel)
    fun inject(authViewModel: AuthViewModel)
    fun inject(changeOrCreateMemoriesViewModel: ChangeOrCreateMemoriesViewModel)
    fun inject(infoMemoriesViewModel: InfoMemoriesViewModel)
    fun inject(listMemoriesViewModel: ListMemoriesViewModel)
    fun inject(profileViewModel: ProfileViewModel)
}