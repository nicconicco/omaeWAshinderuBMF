package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Presenter

import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin
import com.nico.projetopadroesnico.Common.Presenter.BasePresenter
import com.nico.projetopadroesnico.Common.Presenter.BaseView


class DetailMoneyContract {

    interface View : BaseView {
        fun showCoinsFavorite(coins: MutableList<Coin>)
    }

    interface Presenter : BasePresenter<View> {
        fun findAllCoin()
        fun addCoinToFavorite()
        fun initRealm()
        fun setCoin(coin: Coin)
    }
}