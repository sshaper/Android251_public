package com.ebookfrenzy.applinking;

import com.ebookfrenzy.applinking.provider.MyContentProvider;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.ContentResolver;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "Database";
    private ContentResolver myCR;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "landmarks.db";
    public static final String TABLE_LOCATIONS = "locations";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PERSONAL = "personal";

    private static final int id_col = 0;
    private static final int title_col = 1;
    private static final int desc_col = 2;
    private static final int personal_col = 3;

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        myCR = context.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);
    }

    public void addLandmark(Landmark landmark) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, landmark.getID());
        values.put(COLUMN_TITLE, landmark.getTitle());
        values.put(COLUMN_DESCRIPTION, landmark.getDescription());
        values.put(COLUMN_PERSONAL, landmark.getPersonal());

        myCR.insert(MyContentProvider.CONTENT_URI, values);

    }

    public ArrayList<Landmark> findAllLandmarks() {

        ArrayList<Landmark> landmarks = new ArrayList();


        String[] projection = {COLUMN_ID,
                COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_PERSONAL };

        String selection = null;

        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI,
                projection, selection, null,
                null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                Landmark landmark = new Landmark();

                landmark.setID(cursor.getString(id_col));
                landmark.setTitle(cursor.getString(title_col));
                landmark.setDescription(cursor.getString(desc_col));
                landmark.setPersonal(cursor.getInt(personal_col));
                landmarks.add(landmark);
                cursor.moveToNext();

            }
        }
        //cursor.close();
        return landmarks;
    }

    public Landmark findLandmark(String landmarkId) {
        String[] projection = {COLUMN_ID,
                COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_PERSONAL };

        String selection = "_id = \"" + landmarkId + "\"";

        Log.i(TAG, "SELECTION = " + selection);

        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI,
                projection, selection, null,
                null);

        Landmark landmark = new Landmark();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst(); // IS this needed?
            landmark.setID(cursor.getString(0));
            landmark.setTitle(cursor.getString(1));
            landmark.setDescription(cursor.getString(2));
            landmark.setPersonal(cursor.getInt(3));
            cursor.close();
        } else {
            landmark = null;
        }
        return landmark;

    }

    public boolean deleteLandmark(String landmarkId) {

        boolean result = false;

        String selection = "_id = \"" + landmarkId + "\"";

        int rowsDeleted = myCR.delete(MyContentProvider.CONTENT_URI,
                selection, null);

        if (rowsDeleted > 0)
            result = true;

        return result;
    }

}

