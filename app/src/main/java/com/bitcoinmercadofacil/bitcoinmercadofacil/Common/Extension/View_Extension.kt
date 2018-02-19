package com.nico.projetopadroesnico.Common.Extension

import android.graphics.Bitmap
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bitcoinmercadofacil.bitcoinmercadofacil.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

/**
 * Created by 653835 on 25/10/2017.
 */



fun goneView(view: View?) {
    view?.let {
        it.visibility = View.GONE
    }
}

fun invisibleView(view: View?) {
    view?.let {
        it.visibility = View.INVISIBLE
    }
}

fun showView(view: View?) {
    view?.let {
        it.visibility = View.VISIBLE
    }
}


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Glide.with(context)
                .asBitmap()
                .load(R.mipmap.ic_launcher)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                        resource?.let {
                            setImageBitmap(it)
                        }
                    }
                })
    } else {
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                        resource?.let {
                            setImageBitmap(it)
                        }
                    }
                })
    }
}

fun <T> androidLazy(initializer: () -> T) : Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)
