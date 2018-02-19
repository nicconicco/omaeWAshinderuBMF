package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Presenter

import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlin.properties.Delegates


class CoinRealm {

    private var realm: Realm by Delegates.notNull()

    fun getDefaultInstance() {
        realm = Realm.getDefaultInstance()
    }

    @Throws
    fun addCoin(coin: Coin): Boolean {
        try {
            realm.beginTransaction()
            realm.copyToRealm(coin)
            realm.commitTransaction()
            realm.close()
            return true
        } catch (e: RealmException) {
            e.printStackTrace()
            return false
        }
    }

    fun showAllCoins(): MutableList<Coin> {
        val rl = realm.where(Coin::class.java).findAll()
        return rl.subList(0,rl.size)
    }


    fun destroy() {
        realm.close()
    }
}