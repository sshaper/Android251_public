package com.ebookfrenzy.sqldemo

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues
import android.content.ContentResolver

import com.ebookfrenzy.sqldemo.provider.MyContentProvider

class MyDBHandler(context: Context, name: String?,
                  factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    private val myCR: ContentResolver

    init {
        myCR = context.contentResolver
    }

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

        myCR.insert(MyContentProvider.CONTENT_URI, values)
    }

    fun findCustomer(customername: String): Customer? {
        val projection = arrayOf(COLUMN_ID, COLUMN_CUSTOMERNAME,
            COLUMN_CUSTOMERPHONE)
        val selection = "customername = \"" + customername + "\""

        val cursor = myCR.query(MyContentProvider.CONTENT_URI,
            projection, selection, null, null)
        var customer: Customer? = null
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                cursor.moveToFirst()
                val id = Integer.parseInt(cursor.getString(0))
                val customerName = cursor.getString(1)
                val customerPhone = cursor.getString(2)
                customer = Customer(id, customername, customerPhone)
                cursor.close()
            }
        }
        return customer
    }

    fun deleteCustomer(customername: String): Boolean {
        var result = false
        val selection = "customername = \"" + customername + "\""
        val rowsDeleted = myCR.delete(MyContentProvider.CONTENT_URI,
            selection, null)

        if (rowsDeleted > 0)
            result = true
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