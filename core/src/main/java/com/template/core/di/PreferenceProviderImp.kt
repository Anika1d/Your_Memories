package com.template.core.di

import android.content.Context
import javax.inject.Inject

class PreferenceProviderImp @Inject constructor(val context: Context) :
    PreferenceProvider {

    private val sharedPreferences =
        context.getSharedPreferences("USER_PREFERENCES", Context.MODE_PRIVATE)

    override var token: String? = sharedPreferences.getString("token", null)


    override fun updateToken(newToken: String?) {
        token = newToken
        sharedPreferences.edit().putString("token", token).apply()
    }

    override fun deleteToken() {
        token = null
        sharedPreferences.edit().putString("token", token).apply()
    }
}