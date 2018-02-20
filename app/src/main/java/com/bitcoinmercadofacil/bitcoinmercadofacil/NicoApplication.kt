package com.bitcoinmercadofacil.bitcoinmercadofacil

import android.app.Application
import com.google.android.gms.ads.MobileAds
import io.realm.Realm


class NicoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this, "ca-app-pub-8826335474186276~8567017633")
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