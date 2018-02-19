package com.nico.projetopadroesnico.Features.JSoupHtml.Adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bitcoinmercadofacil.bitcoinmercadofacil.R
import com.nico.projetopadroesnico.Common.Extension.inflate
import com.nico.projetopadroesnico.Common.Extension.loadImg
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin
import kotlinx.android.synthetic.main.adapter_jsoup.view.*

class JsoupAdapter(val context: Context, val list: List<Coin>, val callback: onClickDetail) :
        RecyclerView.Adapter<JsoupAdapter.JsoupViewHolder>() {

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): JsoupViewHolder? {
        val view = parent.inflate(R.layout.adapter_jsoup, false)
        return JsoupViewHolder(view)
    }

    override fun onBindViewHolder(holder: JsoupViewHolder, position: Int) {
        val coin = list[position]
        val view = holder.itemView
        with(view) {
            setUpView(coin, view)
        }
    }

    private fun setUpView(coin: Coin, view: View) {
        coin.imgIcone?.let {
            view.imgIcone.loadImg(it)
        }

        coin.graphic?.let {
            view.tGraphic.loadImg(it)
        }

        view.tColocacao.text = "#${coin.colocacao!!.replace(" ","")}."
        view.tNomeMoeda.text = coin.nomeMoeda

        val valor = coin.change.toString().replace("%","")
        if(valor.toDouble() > 0L) {
            view.tNomeMoeda.setTextColor(ContextCompat.getColor(context, R.color.green_2))
            view.tChange.setTextColor(ContextCompat.getColor(context, R.color.green_2))
        } else {
            view.tChange.setTextColor(ContextCompat.getColor(context, R.color.neon_vermelho))
        }

        view.tChange.text = coin.changeString
        view.tPrice.text = coin.priceString?.replace("$", "$ ")

        view.btnDetail.setOnClickListener {
            callback.onClick(coin)
        }
    }

    class JsoupViewHolder(view: android.view.View) : android.support.v7.widget.RecyclerView.ViewHolder(view)

    interface onClickDetail {
        fun onClick(coin: Coin)
    }
}