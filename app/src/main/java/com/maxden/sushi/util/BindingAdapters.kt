package com.maxden.sushi.util

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.maxden.sushi.R
import com.maxden.sushi.model.AccountModel
import com.maxden.sushi.model.CartModel
import com.maxden.sushi.model.ItemTypeModel
import com.maxden.sushi.ui.SpaceItemDecoration
import com.maxden.sushi.ui.SpaceItemDecorationCart
import com.maxden.sushi.ui.home.ItemAdapter
import com.maxden.sushi.viewmodels.home.HomeViewModel

//Loading image using Glide
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

//For Home Fragment
@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<ItemTypeModel>?
) {
    val adapter = recyclerView.adapter as ItemAdapter
    adapter.submitList(data)
}
@BindingAdapter("padding")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    padding: Int
) {
    val spaceItemDecoration = SpaceItemDecoration(padding)
    recyclerView.addItemDecoration(spaceItemDecoration)
}
@BindingAdapter("apiStatus")
fun bindStatus(progressBar: ProgressBar, status: HomeViewModel.ApiStatus?) {
    when (status) {
        HomeViewModel.ApiStatus.DONE ->
            progressBar.visibility = View.GONE
        HomeViewModel.ApiStatus.LOADING ->
            progressBar.visibility = View.VISIBLE
        HomeViewModel.ApiStatus.ERROR ->
            progressBar.visibility = View.GONE

    }
}
@BindingAdapter("button1G","button2R","button2G","button3R","button3G","cart1")
fun visibility1(
    button1R: ConstraintLayout,
    button1G: ConstraintLayout,
    button2R: ConstraintLayout,
    button2G: ConstraintLayout,
    button3R: ConstraintLayout,
    button3G: ConstraintLayout,
    cartItems: List<CartModel>?
) {
    var added1=false
    var added2=false
    var added3 =false
    if (cartItems.isNullOrEmpty()) {
        println("heyy null")
    } else {
        for (items in cartItems) {
            if (items.id == "1") {
                added1=true
            }
            if (items.id == "2") {
                added2=true
            }
            if (items.id == "3") {
                added3=true
            }
        }
    }
    if (added1){
        button1R.visibility=View.GONE
        button1G.visibility=View.VISIBLE
    }
    else{
        button1R.visibility=View.VISIBLE
        button1G.visibility=View.GONE
    }
    if (added2){
        button2R.visibility=View.GONE
        button2G.visibility=View.VISIBLE
    }
    else{
        button2R.visibility=View.VISIBLE
        button2G.visibility=View.GONE
    }
    if (added3){
        button3R.visibility=View.GONE
        button3G.visibility=View.VISIBLE
    }
    else{
        button3R.visibility=View.VISIBLE
        button3G.visibility=View.GONE
    }
}

@BindingAdapter("recView","payButton","emptyCart")
fun emptyCart(
    layout: LinearLayout,
    recView: ConstraintLayout,
    pay: ConstraintLayout,
    data: List<CartModel>?
) {
    if (data.isNullOrEmpty()){
        layout.visibility = View.VISIBLE
        recView.visibility = View.GONE
        pay.visibility = View.GONE
    }
    else{
        layout.visibility = View.GONE
        recView.visibility = View.VISIBLE
        pay.visibility = View.VISIBLE
    }
}

