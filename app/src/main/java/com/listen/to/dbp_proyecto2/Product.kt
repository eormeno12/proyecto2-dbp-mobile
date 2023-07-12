package com.listen.to.dbp_proyecto2

class Product(id: Int, name: String, category: String, description: String, stock: Int, price: Float, image: String) {
    var id: Int? = null
    var name: String? = null
    var category: String? = null
    var description: String? = null
    var stock: Int? = null
    var price: Float? = null
    var image: String? = null


    init {
        this.id = id
        this.name = name
        this.category = category
        this.description = description
        this.stock = stock
        this.price = price
        this.image = image
    }
}