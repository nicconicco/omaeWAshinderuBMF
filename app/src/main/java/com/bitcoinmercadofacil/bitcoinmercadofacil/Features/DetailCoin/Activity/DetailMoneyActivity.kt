package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import br.com.livroandroid.carros.extensions.fromJson
import br.com.livroandroid.carros.extensions.toJson
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Model.Const.COIN
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Presenter.DetailMoneyContract
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.DetailCoin.Presenter.DetailMoneyPresenter
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin
import com.bitcoinmercadofacil.bitcoinmercadofacil.R
import com.cognizant.dor.Common.Extensions.setupToolbar
import com.nico.projetopadroesnico.Common.Activity.BaseActivity
import com.nico.projetopadroesnico.Common.Extension.loadImg
import kotlinx.android.synthetic.main.activity_detail_money.*
import timber.log.Timber

class DetailMoneyActivity : BaseActivity(), DetailMoneyContract.View {

    lateinit var presenter : DetailMoneyPresenter

    override fun showCoinsFavorite(coins: MutableList<Coin>) {
        Timber.d("$${coins.toJson()}")
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_money)
        setupToolbar(R.id.toolbar, getString(R.string.detalhe_da_moeda))

        initPresenter()
        setInfosInView()
        setButtonInView()
    }

    private fun initPresenter() {
        presenter = DetailMoneyPresenter()
        presenter.initRealm()
    }

    private fun setButtonInView() {
        btnFavoriteCoin.setOnClickListener {
            presenter.addCoinToFavorite()
        }
    }

    private fun setInfosInView() {
        val extras = intent.extras
        extras?.let {
            val coin = fromJson<Coin>(extras.getString(COIN))
            presenter.setCoin(coin)

            tVolume.text = coin.volume24h
            tSupply.text = coin.circulatingSupply
            tMarket.text = coin.marketCap

            coin.imgIcone?.let {
                imgIcone.loadImg(it)
            }

            coin.graphic?.let {
                tGraphic.loadImg(it)
            }

            tColocacao.text = "#${coin.colocacao!!.replace(" ", "")}."
            tNomeMoeda.text = coin.nomeMoeda

            val valor = coin.change.toString().replace("%", "")
            if (valor.toDouble() > 0L) {
                tNomeMoeda.setTextColor(ContextCompat.getColor(this, R.color.green_2))
                tChange.setTextColor(ContextCompat.getColor(this, R.color.green_2))
            } else {
                tChange.setTextColor(ContextCompat.getColor(this, R.color.neon_vermelho))
            }

            tChange.text = coin.changeString
            tPrice.text = coin.priceString
        }
    }
}
