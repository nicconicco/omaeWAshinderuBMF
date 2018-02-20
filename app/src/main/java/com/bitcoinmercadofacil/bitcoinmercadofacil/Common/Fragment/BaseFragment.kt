package com.nico.projetopadroesnico.Common.Fragment

import android.os.Bundle
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import timber.log.Timber


open class BaseFragment : android.support.v4.app.Fragment() {

    lateinit var mInterstitialAd: InterstitialAd

    fun showAd() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Timber.d("The interstitial wasn't loaded yet.")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mInterstitialAd = InterstitialAd(context)
        // Id da propaganda
        mInterstitialAd.adUnitId = "ca-app-pub-8826335474186276/3772542875"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                mInterstitialAd.loadAd(AdRequest.Builder().build())
            }
        }
    }
}