package com.ebookfrenzy.sqldemo.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.content.UriMatcher
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.text.TextUtils

import com.ebookfrenzy.sqldemo.MyDBHandler

class MyContentProvider : ContentProvider() {

    private var myDB: MyDBHandler? = null
    private val CUSTOMERS = 1
    private val CUSTOMER_ID = 2

    private val sURIMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        sURIMatcher.addURI(AUTHORITY, CUSTOMERS_TABLE, CUSTOMERS)
        sURIMatcher.addURI(AUTHORITY, CUSTOMERS_TABLE + "/#",
            CUSTOMER_ID)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val uriType = sURIMatcher.match(uri)
        val sqlDB = myDB!!.writableDatabase
        val rowsDeleted: Int

        when (uriType) {
            CUSTOMERS -> rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_CUSTOMERS,
                selection,
                selectionArgs)
            CUSTOMER_ID -> {
                val id = uri.lastPathSegment
                rowsDeleted = if (TextUtils.isEmpty(selection)) {
                    sqlDB.delete(MyDBHandler.TABLE_CUSTOMERS,
                        MyDBHandler.COLUMN_ID + "=" + id,
                        null)
                } else {
                    sqlDB.delete(MyDBHandler.TABLE_CUSTOMERS,
                        MyDBHandler.COLUMN_ID + "=" + id
                                + " and " + selection,
                        selectionArgs)
                }
            }
            else -> throw IllegalArgumentException("Unknown URI: " + uri)
        }
        context?.contentResolver?.notifyChange(uri, null)
        return rowsDeleted
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val uriType = sURIMatcher.match(uri)
        val sqlDB = myDB!!.writableDatabase
        val id: Long

        when (uriType) {
            CUSTOMERS -> id = sqlDB.insert(MyDBHandler.TABLE_CUSTOMERS, null, values)
            else -> throw IllegalArgumentException("Unknown URI: " + uri)
        }

        context?.contentResolver?.notifyChange(uri, null)
        return Uri.parse(CUSTOMERS_TABLE + "/" + id)
    }

    override fun onCreate(): Boolean {
        myDB = context?.let { MyDBHandler(it, null, null, 1) }
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = MyDBHandler.TABLE_CUSTOMERS
        val uriType = sURIMatcher.match(uri)

        when (uriType) {
            CUSTOMER_ID -> queryBuilder.appendWhere(MyDBHandler.COLUMN_ID + "="
                    + uri.lastPathSegment)
            CUSTOMERS -> {
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }

        val cursor = queryBuilder.query(myDB?.readableDatabase,
            projection, selection, selectionArgs, null, null,
            sortOrder)
        cursor.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val uriType = sURIMatcher.match(uri)
        val sqlDB: SQLiteDatabase = myDB!!.writableDatabase
        val rowsUpdated: Int

        when (uriType) {
            CUSTOMERS -> rowsUpdated = sqlDB.update(MyDBHandler.TABLE_CUSTOMERS,
                values,
                selection,
                selectionArgs)
            CUSTOMER_ID -> {
                val id = uri.lastPathSegment
                rowsUpdated = if (TextUtils.isEmpty(selection)) {
                    sqlDB.update(MyDBHandler.TABLE_CUSTOMERS,
                        values,
                        MyDBHandler.COLUMN_ID + "=" + id, null)
                } else {
                    sqlDB.update(MyDBHandler.TABLE_CUSTOMERS,
                        values,
                        MyDBHandler.COLUMN_ID + "=" + id
                                + " and "
                                + selection,
                        selectionArgs)
                }
            }
            else -> throw IllegalArgumentException("Unknown URI: " + uri)
        }
        context?.contentResolver?.notifyChange(uri, null)
        return rowsUpdated
    }

    companion object {
        val AUTHORITY = "com.ebookfrenzy.sqldemo.provider.MyContentProvider"
        private val CUSTOMERS_TABLE = "customers"
        val CONTENT_URI : Uri = Uri.parse("content://" + AUTHORITY + "/" +
                CUSTOMERS_TABLE)
    }
}