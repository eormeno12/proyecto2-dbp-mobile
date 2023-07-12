package com.listen.to.dbp_proyecto2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class AdaptadorCustomProducts(var context: Context, products:ArrayList<Product>):RecyclerView.Adapter<AdaptadorCustomProducts.ViewHolder>() {

    var products:ArrayList<Product>? = null
    var viewHolder:ViewHolder? = null

    init {
        this.products = products
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.template_product_card, parent, false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return products?.count()!!
    }

    fun addProduct(product:Product){
        products?.add(product)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products?.get(position)

        Picasso.get().load(product?.image).into(holder.imageProduct)
        holder.textProductName?.text = product?.name
        holder.textProductCategory?.text = product?.category
        holder.textProductPrice?.text = "S/${product?.price}"
    }

    class ViewHolder(vista:View):RecyclerView.ViewHolder(vista) {
        var imageProduct:ImageView? = null
        var textProductName:TextView? = null
        var textProductCategory:TextView? = null
        var textProductPrice:TextView? = null

        init {
            imageProduct = vista.findViewById(R.id.imageProduct)
            textProductName = vista.findViewById(R.id.textProductName)
            textProductCategory = vista.findViewById(R.id.textProductCategory)
            textProductPrice = vista.findViewById(R.id.textProductPrice)

        }
    }
}