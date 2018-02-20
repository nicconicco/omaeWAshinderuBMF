package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.HttpService

import br.com.livroandroid.carros.extensions.toJson
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import timber.log.Timber
import java.util.*


object JsoupService {
    val MAX = 200

    var list: MutableList<Coin> = mutableListOf()

    fun getHtml(html: String): Document? {
        return Jsoup.connect(html).get()
    }

    fun convertToObjectHtml(doc: Document, count: Int): MutableList<Coin> {
        return transformToCoin(doc, count)
    }

    fun sortByPrice(list: MutableList<Coin>) {
        sortByPrice(list, 0, list.size - 1)
    }

    fun sortByPrice(list: List<Coin>, from: Int, to: Int) {
        if (from < to) {
            var left = from + 1
            var right = to
            val pivotValue = list[from]
            while (left <= right) {
                // left <= to -> limit protection
                while (left <= to && pivotValue.price >= list[left].price) {
                    left++
                }
                // right > from -> limit protection
                while (right > from && pivotValue.price < list[right].price) {
                    right--
                }
                if (left < right) {
                    Collections.swap(list, left, right)
                }
            }
            Collections.swap(list, from, left - 1)
            sortByPrice(list, from, right - 1) // <-- pivot was wrong!
            sortByPrice(list, right + 1, to)   // <-- pivot was wrong!
        }
    }

    private fun transformToCoin(doc: Document, count: Int): MutableList<Coin> {
        val childNodes = doc.select("tbody")
        var listCoins = mutableListOf<Coin>()
        childNodes?.let {
            val coins = it[0].childNodes()

            var count = count
            if (coins.size < count) {
                count = MAX
            }

            var i = 1
            while (i <= count) {

                var colocacao = ""
                if (coins[i].childNodes()[1].childNodes()[0] != null) {
                    colocacao = coins[i].childNodes()[1].childNodes()[0].toString()
                }

                var imgIcone = ""
                if ((coins[i].childNodes()[3].childNodes()[1] as Element).attr("src") != null) {
                    imgIcone = (coins[i].childNodes()[3].childNodes()[1] as Element).attr("src").toString()
                }

                var nomeMoeda = ""
                if ((coins[i].childNodes()[3].childNodes()[1] as Element).attr("alt") != null) {
                    nomeMoeda = (coins[i].childNodes()[3].childNodes()[1] as Element).attr("alt").toString()
                }

                var marketCap = ""
                if (coins[i].childNodes()[5].childNodes()[0] != null) {
                    marketCap = coins[i].childNodes()[5].childNodes()[0].toString()
                }

                var price = ""
                if (coins[i].childNodes()[7].childNodes()[1].childNode(0) != null) {
                    price = coins[i].childNodes()[7].childNodes()[1].childNode(0).toString()
                }

                var volume24h = ""
                if (coins[i].childNodes()[9].childNodes()[1].childNode(0) != null) {
                    volume24h = coins[i].childNodes()[9].childNodes()[1].childNode(0).toString()
                }

                var circulatingSupply = ""
                if (coins[i].childNodes()[11].childNodes()[1].childNode(1).childNode(0) != null) {
                    circulatingSupply = coins[i].childNodes()[11].childNodes()[1].childNode(1).childNode(0).toString()
                }

                var change = ""
                if (coins[i].childNodes()[13].childNodes()[0] != null) {
                    change = coins[i].childNodes()[13].childNodes()[0].toString()
                }

                var graphic = ""
                if ((coins[i].childNodes()[15].childNodes()[0].childNode(0) as Element).attr("src") != null) {
                    graphic = (coins[i].childNodes()[15].childNodes()[0].childNode(0) as Element).attr("src").toString()
                }

                val c = Coin()
                c.colocacao = colocacao
                c.imgIcone = imgIcone
                c.nomeMoeda = nomeMoeda
                c.marketCap = marketCap
                c.priceString = price
                if (price != "") {
                    c.price = price.replace("$", "").toDouble()
                }
                c.volume24h = volume24h
                c.circulatingSupply = circulatingSupply
                c.changeString = change
                if (change != "") {
                    c.change = change.replace("%", "").toDouble()
                }
                c.graphic = graphic

                i += 2
                listCoins.add(c)
                Timber.d("Moeda:\n${c.toJson()}")
            }
        }
        return listCoins
    }

    fun convertToObjectHtmlHighPrice(doc: Document, count: Int): List<Coin> {
        val listCoins = transformToCoin(doc, count)
        this.list = listCoins
        sortByPrice(list)
        Collections.reverse(list)
        list.let {
            return it
        }
    }

    fun convertToObjectHtmlLowPrice(doc: Document, count: Int): List<Coin> {
        val listCoins = transformToCoin(doc, count)
        this.list = listCoins
        sortByPrice(list)
        list.let {
            return it
        }
    }

    fun convertToObjectHtmlMaxChange(doc: Document, count: Int): List<Coin> {
        val listCoins = transformToCoin(doc, count)
        return listCoins.sortedWith(compareBy({it.change})).reversed()
//
//        val listCoins = transformToCoin(doc, count)
//        this.list = listCoins
//        sortByChange(list!!)
//        Collections.reverse(list)
//        list.let {
//            return it
//        }
    }

    fun convertToObjectHtmlLowChange(doc: Document, count: Int): List<Coin> {
        val listCoins = transformToCoin(doc, count)
        return listCoins.sortedWith(compareBy({it.change}))
    }

    private fun sortByChange(list: MutableList<Coin>) {
        sortByChange(list, 0, list.size - 1)
    }

    fun sortByChange(list: List<Coin>, from: Int, to: Int) {
        if (from < to) {
            var left = from + 1
            var right = to
            val pivotValue = list[from]
            while (left <= right) {
                // left <= to -> limit protection
                while (left <= to && pivotValue.change >= list[left].change) {
                    left++
                }
                // right > from -> limit protection
                while (right > from && pivotValue.change < list[right].change) {
                    right--
                }
                if (left < right) {
                    Collections.swap(list, left, right)
                }
            }
            Collections.swap(list, from, left - 1)
            sortByPrice(list, from, right - 1) // <-- pivot was wrong!
            sortByPrice(list, right + 1, to)   // <-- pivot was wrong!
        }
    }

    fun getListMenu(): MutableList<String> {

        var list = mutableListOf<String>()
        list.add("Mercado atual")
        list.add("Moeda mais valorizada $")
        list.add("Moeda menos valorizada $")
        list.add("Moeda com maior % de crescimento")
        list.add("Moeda com menor % de crescimento")
        return list
    }
}