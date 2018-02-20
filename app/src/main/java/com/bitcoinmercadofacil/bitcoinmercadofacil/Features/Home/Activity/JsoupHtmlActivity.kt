package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Fragment.JsoupHtmlFragment
import com.bitcoinmercadofacil.bitcoinmercadofacil.R
import com.cognizant.dor.Common.Extensions.addFragment
import com.cognizant.dor.Common.Extensions.setupToolbar
import com.nico.projetopadroesnico.Common.Activity.BaseActivity
import android.view.Menu
import android.view.ViewGroup
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Adapter.MenuHomeAdapter
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.Coin
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Model.HttpService.JsoupService
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.LowPercent.Fragment.LowPercent
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.LowPrice.Fragment.LowPriceFragment
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.PlusPercent.Fragment.PlusPercent
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.PlusPrice.Fragment.PlusPriceFragment
import com.cognizant.dor.Common.Extensions.replace
import com.nico.projetopadroesnico.Common.Extension.inflate
import com.nico.projetopadroesnico.Common.Util.InfiniteScrollListener
import com.nico.projetopadroesnico.Features.JSoupHtml.Adapter.JsoupAdapter
import kotlinx.android.synthetic.main.activity_jsoup_html.*
import kotlinx.android.synthetic.main.adapter_menu_side.view.*
import kotlinx.android.synthetic.main.header_menu_side.*


class JsoupHtmlActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    var drawer: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jsoup_html)

        setupToolbar(R.id.toolbar, getString(R.string.bitcoin_mercado_facil))
        initFragmentInActivity(savedInstanceState)
        setDrawerLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setDrawerLayout() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val linearLayout = LinearLayoutManager(context)
        recyclerMenuSide.layoutManager = linearLayout
        recyclerMenuSide.itemAnimator = DefaultItemAnimator()
        recyclerMenuSide.setHasFixedSize(true)

        recyclerMenuSide.clearOnScrollListeners()
        recyclerMenuSide.addOnScrollListener(InfiniteScrollListener({ }, linearLayout))

        recyclerMenuSide.adapter = MenuHomeAdapter(JsoupService.getListMenu(),
                object : MenuHomeAdapter.onClickMenu {
                    override fun onClick(position: Int) {
                        when (position) {
                            0 -> {
                                replace(R.id.containerPrincipal, JsoupHtmlFragment())
                            }
                            1 -> {
                                replace(R.id.containerPrincipal, PlusPriceFragment())
                            }
                            2 -> {
                                replace(R.id.containerPrincipal, LowPriceFragment())
                            }
                            3 -> {
                                replace(R.id.containerPrincipal, PlusPercent())
                            }
                            4 -> {
                                replace(R.id.containerPrincipal, LowPercent())
                            }
                        }

                        drawer?.closeDrawer(GravityCompat.START)
                    }
                })
    }

    private fun initFragmentInActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(R.id.containerPrincipal, JsoupHtmlFragment())
        }
    }

}

