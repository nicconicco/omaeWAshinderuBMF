package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Presenter

import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Fragment.JsoupHtmlFragment
import com.nico.projetopadroesnico.Common.Presenter.BasePresenter
import com.nico.projetopadroesnico.Common.Presenter.BaseView
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin

class JsoupHtmlContract {

    interface View : BaseView {
        fun showProgress()
        fun hidePorgress()
        fun showParseHtml(listCoins: MutableList<Coin>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadByFlagSearch(fragment: JsoupHtmlFragment, flag: Int)
        fun loadHtml(fragment: JsoupHtmlFragment)
        fun loadLowPrice(fragment: JsoupHtmlFragment)
        fun loadMaxChange(fragment: JsoupHtmlFragment)
        fun loadLowChange(fragment: JsoupHtmlFragment)
        fun loadHighPrice(jsoupHtmlFragment: JsoupHtmlFragment)
    }

}