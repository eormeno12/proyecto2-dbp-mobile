package com.listen.to.dbp_proyecto2

import android.content.Context
import android.media.MediaRouter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.appconocimientostotales.ClickListener
import java.util.*
import kotlin.collections.ArrayList

class AdaptadorCustom(var context: Context, orders:ArrayList<Order>, var clickListener:ClickListener):RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var orders:ArrayList<Order>? = null
    var viewHolder:ViewHolder? = null

    init {
        this.orders = orders
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.template_order_card, parent, false)
        viewHolder = ViewHolder(vista, clickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return orders?.count()!!
    }

    fun addOrder(order:Order){
        orders?.add(order)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders?.get(position)
        holder.textOrderTitle?.text = "Pedido ${order?.id}"
        holder.textOrderDate?.text = "${order?.date}"
        holder.textOrderArticles?.text = "${order?.products?.split(',')?.count()} artículos"
    }

    class ViewHolder(vista:View, clickListener: ClickListener):RecyclerView.ViewHolder(vista), View.OnClickListener{
        var textOrderTitle:TextView? = null
        var textOrderDate:TextView? = null
        var textOrderArticles:TextView? = null
        var clickListener:ClickListener? = null

        init {
            textOrderTitle = vista.findViewById(R.id.textOrderTitle)
            textOrderDate = vista.findViewById(R.id.textOrderDate)
            textOrderArticles = vista.findViewById(R.id.textOrderArticles)

            this.clickListener = clickListener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener?.ClickListener(v!!, adapterPosition)
        }
    }
}