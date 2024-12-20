package com.example.productdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")
    var id: Int = 0

    @ColumnInfo(name = "productName")
    var productName: String? = null

    var quantity: Int = 0

    constructor() {}

    constructor(productname: String, quantity: Int) {
        this.productName = productname
        this.quantity = quantity
    }
}