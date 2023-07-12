package com.listen.to.dbp_proyecto2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class OrderDetails: AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var textDetailsTitle: TextView? = null
    var textDetailsDNI: TextView? = null
    var textDetailsDate: TextView? = null
    var textDetailsTotalPrice: TextView? = null
    var toolbar: Toolbar? = null


    var adapter: AdaptadorCustomProducts? = null
    var products = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_details)

        textDetailsTitle = this.findViewById(R.id.textDetailsTitle)
        textDetailsDNI = this.findViewById(R.id.textDetailsDNI)
        textDetailsDate = this.findViewById(R.id.textDetailsDate)
        textDetailsTotalPrice = this.findViewById(R.id.textDetailsTotalPrice)

        configAdaptadorCustom()

        val id = intent.getIntExtra("com.example.appconocimientostotales.OrderID", 0)

        toolbar = findViewById(R.id.toolbarDetails)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Detalles Pedido ${id}"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        loadOrderByID(id)
    }

    override fun onStart() {
        super.onStart()
        adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()

    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.notifyDataSetChanged()
    }

    //Inicalizar en onCreate
    fun configAdaptadorCustom() {
        //Establecer Recycler View
        recyclerView = findViewById(R.id.productsRecyclerView)

        //Establecer Layout Manager
        layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager


        adapter = AdaptadorCustomProducts(this, products)

        recyclerView?.adapter = adapter
    }

    fun loadOrderByID(id: Int){
        val apiRequest = APIRequest()

        apiRequest.getOrderById(id).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                this@OrderDetails.runOnUiThread {
                    val Gson = Gson()
                    val order = Gson.fromJson(data, OrderFull::class.java)

                    textDetailsTitle?.text = "Pedido ${order.id}"
                    textDetailsDNI?.text = "DNI: ${order.userID}"
                    textDetailsDate?.text = "Fecha: ${order.date}"
                    textDetailsTotalPrice?.text = "Total: S/${order.totalPrice}"

                    order.products?.forEach { product ->
                        adapter?.addProduct(product)
                    }
                }
            }
        })
    }
}