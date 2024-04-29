package com.ebookfrenzy.sqldemo

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class MyDBHandler(context: Context, name: String?,
                  factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CUSTOMERS_TABLE = ("CREATE TABLE " +
                TABLE_CUSTOMERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_CUSTOMERNAME
                + " TEXT," + COLUMN_CUSTOMERPHONE + " TEXT" + ")")

        db.execSQL(CREATE_CUSTOMERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS)
        onCreate(db)
    }

    fun addCustomer(customer: Customer) {
        val values = ContentValues()

        values.put(COLUMN_CUSTOMERNAME, customer.customerName)
        values.put(COLUMN_CUSTOMERPHONE, customer.customerPhone)
        val db = this.writableDatabase
        db.insert(TABLE_CUSTOMERS, null, values)
        db.close()
    }

    fun findCustomer(customername: String): Customer? {
        val query =
            "SELECT * FROM $TABLE_CUSTOMERS WHERE $COLUMN_CUSTOMERNAME = \"$customername\""

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var customer: Customer? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val phone = cursor.getString(2)
            customer = Customer(id, name, phone)
            cursor.close()
        }
        db.close()
        return customer
    }

    fun deleteCustomer(customername: String): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_CUSTOMERS WHERE $COLUMN_CUSTOMERNAME = \"$customername\""

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(
                TABLE_CUSTOMERS, COLUMN_ID + " = ?",
                arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "customerDB.db"
        val TABLE_CUSTOMERS = "customers"
        val COLUMN_ID = "_id"
        val COLUMN_CUSTOMERNAME = "customername"
        val COLUMN_CUSTOMERPHONE = "customerphone"
    }

}