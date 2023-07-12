package com.listen.to.dbp_proyecto2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconocimientostotales.ClickListener
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    var orders = ArrayList<Order>()

    var adapter: AdaptadorCustom? = null

    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        configAdaptadorCustom()
        loadOrders()

        toolbar = findViewById(R.id.toolbar)
        toolbar?.title = "PAW STYLE"
        setSupportActionBar(toolbar)
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

    fun configAdaptadorCustom() {
        recyclerView = findViewById(R.id.ordersRecyclerView)

        layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager

        adapter = AdaptadorCustom(this, orders, object : ClickListener {
            override fun ClickListener(view: View, position: Int) {

                val orderClicked = adapter?.orders?.get(position)

                val intent = Intent(applicationContext, OrderDetails::class.java).apply {
                    putExtra("com.example.appconocimientostotales.OrderID", orderClicked?.id)
                }

                startActivity(intent)
            }
        })

        recyclerView?.adapter = adapter
    }

    fun loadOrders(){
        val apiRequest = APIRequest()

        apiRequest.getOrders().enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                this@MainActivity.runOnUiThread {
                    val Gson = Gson()
                    val ordersListType = object : TypeToken<List<Order>>() {}.type
                    val orders = Gson.fromJson<List<Order>>(data, ordersListType)

                    for(order in orders){
                        adapter?.addOrder(order)
                    }
                }
            }
        })
    }
}