package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Presenter

import android.support.v4.app.Fragment
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Fragment.JsoupHtmlFragment
import com.nico.projetopadroesnico.Common.Presenter.BasePresenter
import com.nico.projetopadroesnico.Common.Presenter.BaseView
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin

class JsoupHtmlContract {

    interface View : BaseView {
        fun showProgress()
        fun hidePorgress()
        fun showParseHtml(listCoins: List<Coin>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadByFlagSearch(fragment: Fragment, flag: Int)
        fun loadHtml(fragment: Fragment)
        fun loadLowPrice(fragment: Fragment)
        fun loadMaxChange(fragment: Fragment)
        fun loadLowChange(fragment: Fragment)
        fun loadHighPrice(jsoupHtmlFragment: Fragment)
    }

}