package com.ebookfrenzy.sqldemo

class Customer {

    var id: Int = 0
    var customerName: String?  = null
    var customerPhone: String? = null

    constructor(id: Int, name: String, phone: String ) {
        this.id = id
        this.customerName = name
        this.customerPhone = phone
    }
    constructor(name: String, phone: String) {
        this.customerName = name
        this.customerPhone = phone
    }
}