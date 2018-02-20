package com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Adapter

import android.support.v7.widget.RecyclerView
import com.bitcoinmercadofacil.bitcoinmercadofacil.Features.Home.Activity.JsoupHtmlActivity
import com.bitcoinmercadofacil.bitcoinmercadofacil.R
import com.nico.projetopadroesnico.Common.Extension.inflate
import kotlinx.android.synthetic.main.adapter_menu_side.view.*

class MenuHomeAdapter(val list: List<String>, private val callback: onClickMenu) : RecyclerView.Adapter<MenuHomeAdapter.MenuViewHolder>() {
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuIcon = list[position]
        val view = holder.itemView
        with(view) {
            tName.text = menuIcon

            btnMenu.setOnClickListener {
                callback.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): MenuViewHolder {
        val view = parent.inflate(R.layout.adapter_menu_side, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    class MenuViewHolder(view: android.view.View) : android.support.v7.widget.RecyclerView.ViewHolder(view)

    interface onClickMenu {
        fun onClick(position: Int)
    }
}
