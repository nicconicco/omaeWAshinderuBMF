package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Presenter

import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin

class DetailMoneyPresenter : DetailMoneyContract.Presenter {
    override fun initRealm() {
        realm = CoinRealm()
        realm.getDefaultInstance()
    }

    private var coinInView: Coin? = null
    private var view: DetailMoneyContract.View? = null
    lateinit var realm : CoinRealm

    override fun setCoin(coin: Coin) {
        this.coinInView = coin
    }

    override fun findAllCoin() {
        view?.showCoinsFavorite(realm.showAllCoins())
    }

    override fun addCoinToFavorite() {
        this.coinInView?.let {
            if(realm.addCoin(it)) {
                findAllCoin()
            }
        }
    }

    override fun detachView(view: DetailMoneyContract.View) {
        this.view = null
    }

    override fun attachView(view: DetailMoneyContract.View) {
        this.view = view
    }

    override fun destroy() {
        realm.destroy()
    }
}