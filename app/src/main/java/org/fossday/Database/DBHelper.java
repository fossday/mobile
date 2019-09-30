package org.fossday.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.fossday.Database.Contract.DBContract;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 13;
    private static final String DATABASE_NAME = "FOSSDay.db";

    private static final String SQL_CREATE_CATEGORIES =
            "CREATE TABLE " + DBContract.Categories.TABLE_NAME + " (" +
                    DBContract.Speakers._ID + " INTEGER PRIMARY KEY," +
                    DBContract.Speakers.COLUMN_EID + " INTEGER," +
                    DBContract.Speakers.COLUMN_NAME + " TEXT)";

    private static final String SQL_CREATE_SPEAKERS =
            "CREATE TABLE " + DBContract.Speakers.TABLE_NAME + " (" +
                    DBContract.Speakers._ID + " INTEGER PRIMARY KEY," +
                    DBContract.Speakers.COLUMN_EID + " INTEGER," +
                    DBContract.Speakers.COLUMN_NAME + " TEXT," +
                    DBContract.Speakers.COLUMN_POSITION + " TEXT," +
                    DBContract.Speakers.COLUMN_DESCRIPTION + " TEXT," +
                    DBContract.Speakers.COLUMN_FACEBOOK + " TEXT," +
                    DBContract.Speakers.COLUMN_INSTAGRAM + " TEXT," +
                    DBContract.Speakers.COLUMN_TWITTER + " TEXT," +
                    DBContract.Speakers.COLUMN_LINKEDIN + " TEXT," +
                    DBContract.Speakers.COLUMN_YOUTUBE + " TEXT, " +
                    DBContract.Speakers.COLUMN_GITHUB + " TEXT)";

    private static final String SQL_CREATE_SPONSORS =
            "CREATE TABLE " + DBContract.Sponsors.TABLE_NAME + " (" +
                    DBContract.Sponsors._ID + " INTEGER PRIMARY KEY," +
                    DBContract.Sponsors.COLUMN_EID + " INTEGER," +
                    DBContract.Sponsors.COLUMN_NAME + " TEXT," +
                    DBContract.Sponsors.COLUMN_WEBSITE + " TEXT," +
                    DBContract.Sponsors.COLUMN_TYPE + " TEXT)";

    private static final String SQL_CREATE_LECTURES =
            "CREATE TABLE " + DBContract.Lectures.TABLE_NAME + " (" +
                    DBContract.Lectures._ID + " INTEGER PRIMARY KEY," +
                    DBContract.Lectures.COLUMN_NAME + " TEXT," +
                    DBContract.Lectures.COLUMN_DESCRIPTION + " TEXT," +
                    DBContract.Lectures.COLUMN_PERIOD + " TEXT," +
                    DBContract.Lectures.COLUMN_TIME + " INTEGER," +
                    DBContract.Lectures.COLUMN_ROOM + " TEXT," +
                    DBContract.Lectures.COLUMN_SPEAKER_ID + " INTEGER," +
                    " FOREIGN KEY ("+DBContract.Lectures.COLUMN_SPEAKER_ID+")" +
                    " REFERENCES "+DBContract.Speakers.TABLE_NAME+"(" +
                    DBContract.Speakers.COLUMN_EID +"));";

    private static final String SQL_DELETE_CATEGORIES =
            "DROP TABLE IF EXISTS " + DBContract.Categories.TABLE_NAME;

    private static final String SQL_DELETE_SPEAKERS =
            "DROP TABLE IF EXISTS " + DBContract.Speakers.TABLE_NAME;

    private static final String SQL_DELETE_SPONSORS =
            "DROP TABLE IF EXISTS " + DBContract.Sponsors.TABLE_NAME;

    private static final String SQL_DELETE_LECTURES =
            "DROP TABLE IF EXISTS " + DBContract.Lectures.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CATEGORIES);
        db.execSQL(SQL_CREATE_SPEAKERS);
        db.execSQL(SQL_CREATE_SPONSORS);
        db.execSQL(SQL_CREATE_LECTURES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_LECTURES);
        db.execSQL(SQL_DELETE_SPONSORS);
        db.execSQL(SQL_DELETE_SPEAKERS);
        db.execSQL(SQL_DELETE_CATEGORIES);

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
