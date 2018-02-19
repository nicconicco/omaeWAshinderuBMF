package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Coin : RealmObject() {

    @PrimaryKey
    var id: Long = 0

    var colocacao: String? = ""
    var imgIcone: String? = ""
    var nomeMoeda: String? = ""
    var marketCap: String? = ""
    var priceString: String? = ""
    var price: Double = 0.0
    var volume24h: String? = ""
    var circulatingSupply: String? = ""
    var changeString: String? = ""
    var change: Double = 0.0
    var graphic: String? = ""
}