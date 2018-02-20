package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.LowPercent.Fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.livroandroid.carros.extensions.toJson
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Activity.DetailMoneyActivity
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Model.Const
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Presenter.JsoupHtmlContract
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Presenter.JsoupHtmlPresenter
import com.bitcoinmercadofacil.bitcoinmercadofacil.R
import com.google.android.gms.ads.AdView
import com.nico.projetopadroesnico.Common.Extension.goneView
import com.nico.projetopadroesnico.Common.Extension.showView
import com.nico.projetopadroesnico.Common.Fragment.BaseFragment
import com.nico.projetopadroesnico.Common.Util.InfiniteScrollListener
import com.nico.projetopadroesnico.Features.JSoupHtml.Adapter.JsoupAdapter
import kotlinx.android.synthetic.main.fragment_plus_price.*
import org.jetbrains.anko.startActivity

class LowPercent : BaseFragment() , JsoupHtmlContract.View, JsoupAdapter.onClickDetail{

    override fun showProgress() {
        showView(progressBarCoin)
    }

    override fun hidePorgress() {
        goneView(progressBarCoin)
    }

    private var list = listOf<Coin>()
    lateinit var presenter: JsoupHtmlPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, icicle: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_low_percent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewButtons()
        initPresenter()
        initRecyclerView()
        activity?.let {
            presenter.showAdmobAtBottom(it, view.findViewById(R.id.adView) as AdView)
        }
    }

    private fun initViewButtons() {
        btnAtualizar.setOnClickListener {
            presenter.loadLowChange(this)
            showAd()
        }
    }


    private fun initPresenter() {
        presenter = JsoupHtmlPresenter()
        presenter.attachView(this)
        presenter.loadLowChange(this)
    }

    private fun initRecyclerView() {
        val linearLayout = LinearLayoutManager(context)
        listCriptoMoedas.layoutManager = linearLayout
        listCriptoMoedas.itemAnimator = DefaultItemAnimator()
        listCriptoMoedas.setHasFixedSize(true)

        listCriptoMoedas.clearOnScrollListeners()
        listCriptoMoedas.addOnScrollListener(InfiniteScrollListener({ requestNewCoins() }, linearLayout))
        context?.let {
            listCriptoMoedas.adapter = JsoupAdapter(it, list, this)
        }
    }

    private fun requestNewCoins() {
        presenter.loadByFlagSearch(this, presenter.FLAG_SEARCH)
    }

    override fun showParseHtml(listCoin: List<Coin>) {
        this.list = listCoin
        context?.let {
            listCriptoMoedas.adapter = JsoupAdapter(it, this.list, this)
        }
    }

    override fun onClick(coin: Coin) {
        context?.startActivity<DetailMoneyActivity>(Const.COIN to coin.toJson())
    }
}