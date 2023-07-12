package com.listen.to.dbp_proyecto2

class OrderFull (id: Int, userID: Int, products: ArrayList<Product>, totalPrice: Float, date: String) {
    var id: Int? = null
    var userID: Int? = null
    var products: ArrayList<Product>? = null
    var totalPrice: Float? = null
    var date: String? = null


    init {
        this.id = id
        this.userID = userID
        this.products = products
        this.totalPrice = totalPrice
        this.date = date
    }
}