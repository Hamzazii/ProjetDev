package com.example.projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnnouncementDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AnnouncementDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "announcements";
    private static final String COLUMN_CITY = "city";

    public AnnouncementDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String announcementTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_CITY + " TEXT PRIMARY KEY)";
        db.execSQL(announcementTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addAnnouncementForCity(String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CITY, city);
        db.insert(TABLE_NAME, null, values);
        db.close(); // Ne pas oublier de fermer la base de données après utilisation
    }

    public int getAnnouncementCountForCity(String city) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"COUNT(*)"};
        String selection = COLUMN_CITY + " = ?";
        String[] selectionArgs = {city};
        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }

        return count;
    }
}
