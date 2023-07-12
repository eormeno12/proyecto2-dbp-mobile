package com.listen.to.dbp_proyecto2

import okhttp3.*

class APIRequest(){

    private fun getAPI(path:String):String{
        return "https://dbp-proyecto2-backend-87b7472b2add.herokuapp.com/${path}"
    }

    private fun httpRequest(url: String): Call {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        return client.newCall(request)
    }

    fun getOrders(): Call{
        val ordersURL = getAPI("orders")

        return httpRequest(ordersURL)
    }

    fun getOrderById(id: Int): Call{
        val ordersURL = getAPI("orders/${id}")

        return httpRequest(ordersURL)
    }
}