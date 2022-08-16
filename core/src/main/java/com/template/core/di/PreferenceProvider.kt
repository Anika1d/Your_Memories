package com.template.core.di

interface PreferenceProvider {
    var token: String?
    fun updateToken(newToken: String?)
    fun  deleteToken()
}