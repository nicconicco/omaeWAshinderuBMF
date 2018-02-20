package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Presenter

import android.support.v4.app.Fragment
import br.com.livroandroid.carros.extensions.toJson
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Model.Const.HTML
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.HttpService.JsoupService
import com.cognizant.dor.Common.Extensions.task.startTask
import com.nico.projetopadroesnico.Common.Extension.isNetworkAvailable
import org.jsoup.nodes.Document
import timber.log.Timber
import java.util.concurrent.TimeoutException


class JsoupHtmlPresenter : JsoupHtmlContract.Presenter {

    /**
     *  0 - > Busca normal
     *  1 -> High Price
     *  2 - Low Price
     *  3 -> Low Change
     *  4 -> Max change ( maior % )
     */
    var FLAG_SEARCH = 0
    var RUNNING_TASK = false
    private var view: JsoupHtmlContract.View? = null
    private var count = 0

    override fun attachView(view: JsoupHtmlContract.View) {
        this.view = view
    }

    override fun detachView(view: JsoupHtmlContract.View) {
        this.view = null
    }

    override fun destroy() {
    }

    override fun loadByFlagSearch(fragment: Fragment, flag: Int) {
        if(!RUNNING_TASK) {
            when (flag) {
                0 -> {
                    loadHtml(fragment)
                }
                1 -> {
                    loadHighPrice(fragment)
                }
                2 -> {
                    loadLowPrice(fragment)
                }
                3 -> {
                    loadLowChange(fragment)
                }
                4 -> {
                    loadMaxChange(fragment)
                }
            }
        }
    }

    override fun loadHtml(fragment: Fragment) {
        FLAG_SEARCH = 0

        this.count = this.count + 20
        var doc: Document? = null

        if(!RUNNING_TASK) {
            RUNNING_TASK = true
            view?.showProgress()
            if (fragment.context != null && fragment.activity != null) {
                if (fragment.context!!.isNetworkAvailable()) {
                    startTask(activity = fragment.activity!!,
                            execute = {
                                try {
                                    doc = JsoupService.getHtml(HTML)
                                } catch (e: Exception) {
                                    view?.hidePorgress()
                                    Timber.e(e)
                                } catch (e: TimeoutException) {
                                    Timber.e(e)
                                }
                            }, updateView = {

                        doc?.let {
                            updateView(JsoupService.convertToObjectHtml(it, count))
                        }
                    })
                } else {
                    view?.hidePorgress()
                    Timber.d("No internet")
                }
            }
        }
    }

    override fun loadHighPrice(fragment: Fragment) {
        FLAG_SEARCH = 1

        this.count = this.count + 20
        var doc: Document? = null

        if(!RUNNING_TASK) {
            RUNNING_TASK = true
            view?.showProgress()
            if (fragment.context != null && fragment.activity != null) {
                if (fragment.context!!.isNetworkAvailable()) {
                    startTask(activity = fragment.activity!!,
                            execute = {
                                try {
                                    doc = JsoupService.getHtml(HTML)
                                } catch (e: Exception) {
                                    view?.hidePorgress()
                                    Timber.e(e)
                                } catch (e: TimeoutException) {
                                    Timber.e(e)
                                }
                            }, updateView = {

                        doc?.let {
                            updateView(JsoupService.convertToObjectHtmlHighPrice(it, count))
                        }
                    })
                } else {
                    view?.hidePorgress()
                    Timber.d("No internet")
                }
            }
        }
    }
    override fun loadLowPrice(fragment: Fragment) {
        FLAG_SEARCH = 2

        this.count = this.count + 20
        var doc: Document? = null

        if(!RUNNING_TASK) {
            RUNNING_TASK = true
            view?.showProgress()
            if (fragment.context != null && fragment.activity != null) {
                if (fragment.context!!.isNetworkAvailable()) {
                    startTask(activity = fragment.activity!!,
                            execute = {
                                try {
                                    doc = JsoupService.getHtml(HTML)
                                } catch (e: Exception) {
                                    view?.hidePorgress()
                                    Timber.e(e)
                                } catch (e: TimeoutException) {
                                    Timber.e(e)
                                }
                            }, updateView = {

                        doc?.let {
                            updateView(JsoupService.convertToObjectHtmlLowPrice(it, count))
                        }
                    })
                } else {
                    view?.hidePorgress()
                    Timber.d("No internet")
                }
            }
        }
    }
    override fun loadMaxChange(fragment: Fragment) {
        FLAG_SEARCH = 4

        this.count = this.count + 20
        var doc: Document? = null

        if(!RUNNING_TASK) {
            RUNNING_TASK = true
            view?.showProgress()
            if (fragment.context != null && fragment.activity != null) {
                if (fragment.context!!.isNetworkAvailable()) {
                    startTask(activity = fragment.activity!!,
                            execute = {
                                try {
                                    doc = JsoupService.getHtml(HTML)
                                } catch (e: Exception) {
                                    view?.hidePorgress()
                                    Timber.e(e)
                                } catch (e: TimeoutException) {
                                    Timber.e(e)
                                }
                            }, updateView = {

                        doc?.let {
                            updateView(JsoupService.convertToObjectHtmlMaxChange(it, count))
                        }
                    })
                } else {
                    view?.hidePorgress()
                    Timber.d("No internet")
                }
            }
        }
    }
    override fun loadLowChange(fragment: Fragment) {
        FLAG_SEARCH = 3

        this.count = this.count + 20
        var doc: Document? = null

        if(!RUNNING_TASK) {
            RUNNING_TASK = true
            view?.showProgress()
            if (fragment.context != null && fragment.activity != null) {
                if (fragment.context!!.isNetworkAvailable()) {
                    startTask(activity = fragment.activity!!,
                            execute = {
                                try {
                                    doc = JsoupService.getHtml(HTML)
                                } catch (e: Exception) {
                                    view?.hidePorgress()
                                    Timber.e(e)
                                } catch (e: TimeoutException) {
                                    Timber.e(e)
                                }
                            }, updateView = {

                        doc?.let {
                            updateView(JsoupService.convertToObjectHtmlLowChange(it, count))
                        }
                    })
                } else {
                    view?.hidePorgress()
                    Timber.d("No internet")
                }
            }
        }
    }

    private fun updateView(listCoins: List<Coin>) {
        RUNNING_TASK = false
        Timber.d("Lista final:\n${listCoins.toJson()}")
        view?.hidePorgress()
        view?.showParseHtml(listCoins)
    }
}