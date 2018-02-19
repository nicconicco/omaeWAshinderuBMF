package com.bitcoinmercadofacil.bitcoinmercadofacil

import android.app.Application
import io.realm.Realm


class NicoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        Realm.init(this)
    }

    companion object {
        var appInstance: NicoApplication? = null
        fun getInstance(): NicoApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe Application no AndroidManifest.xml")
            }

            return appInstance!!
        }
    }
}