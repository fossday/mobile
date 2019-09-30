package org.fossday.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.fossday.Database.Contract.DBContract;
import org.fossday.Model.Category;
import org.fossday.Model.Lecture;
import org.fossday.Model.Speaker;
import org.fossday.Model.Sponsor;

import java.util.ArrayList;
import java.util.List;

public class DB {

    private SQLiteDatabase db;

    public DB(Context context) {
        DBHelper dbHelper = new DBHelper(context);

        // Gets the data repository in write mode
        db = dbHelper.getWritableDatabase();
    }

    public void clearTables() {
        db.execSQL("delete from Lectures");
        db.execSQL("delete from Speakers");
        db.execSQL("delete from Sponsors");
        db.execSQL("delete from Categories");
    }

    public long insertCategoy(Category category) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.Categories.COLUMN_EID, category.getId());
        values.put(DBContract.Categories.COLUMN_NAME, category.getName());

        // Insert the new row, returning the primary key value of the new row
        return db.insert(DBContract.Categories.TABLE_NAME, null, values);
    }

    public long insertSpeaker(Speaker speaker) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.Speakers.COLUMN_NAME, speaker.getName());
        values.put(DBContract.Speakers.COLUMN_EID, speaker.getId());
        values.put(DBContract.Speakers.COLUMN_POSITION, speaker.getPosition());
        values.put(DBContract.Speakers.COLUMN_DESCRIPTION, speaker.getDescription());
        values.put(DBContract.Speakers.COLUMN_FACEBOOK, speaker.getFacebook());
        values.put(DBContract.Speakers.COLUMN_INSTAGRAM, speaker.getInstagram());
        values.put(DBContract.Speakers.COLUMN_TWITTER, speaker.getTwitter());
        values.put(DBContract.Speakers.COLUMN_LINKEDIN, speaker.getLinkedin());
        values.put(DBContract.Speakers.COLUMN_YOUTUBE, speaker.getYoutube());
        values.put(DBContract.Speakers.COLUMN_GITHUB, speaker.getGithub());

        // Insert the new row, returning the primary key value of the new row
        return db.insert(DBContract.Speakers.TABLE_NAME, null, values);
    }

    public List<Speaker> getSpeakers() {
        List<Speaker> list = new ArrayList<>();
        String[] columns = new String[]{"_id", "eid", "name", "position", "description"};

        Cursor cursor = db.query(
                "Speakers",
                columns,
                null,
                null,
                null,
                null,
                null
        );

        // Verify if there are results
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Speaker speaker = new Speaker();
                speaker.setId(cursor.getInt(1));
                speaker.setName(cursor.getString(2));
                speaker.setPosition(cursor.getString(3));
                speaker.setDescription(cursor.getString(4));

                list.add(speaker);
            } while (cursor.moveToNext());

        }

        cursor.close();
        return(list);
    }


    public void insertSponsor(Sponsor sponsor) {
        // Create a new map of values, where column names are the keys

        Log.d("INSERT", "insertSponsor: " + sponsor.getEid());

        ContentValues values = new ContentValues();
        values.put(DBContract.Sponsors.COLUMN_EID, sponsor.getEid());
        values.put(DBContract.Sponsors.COLUMN_NAME, sponsor.getName());
        values.put(DBContract.Sponsors.COLUMN_WEBSITE, sponsor.getWebsite());
        values.put(DBContract.Sponsors.COLUMN_TYPE, sponsor.getType());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBContract.Sponsors.TABLE_NAME, null, values);
    }

    public List<Sponsor> getSponsors() {
        List<Sponsor> list = new ArrayList<>();
        String[] columns = new String[]{"_id", "eid", "name", "website", "type"};

        Cursor cursor = db.query(
                "Sponsors",
                columns,
                null,
                null,
                null,
                null,
                null
        );

        // Verify if there are results
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Sponsor sponsor = new Sponsor();
                sponsor.setEid(cursor.getInt(1));
                sponsor.setName(cursor.getString(2));
                sponsor.setWebsite(cursor.getString(3));
                sponsor.setType(cursor.getString(4));

                list.add(sponsor);
            } while (cursor.moveToNext());

        }

        cursor.close();
        return(list);
    }

    public List<Sponsor> getSupporters() {
        List<Sponsor> list = new ArrayList<>();
        String[] columns = new String[]{"_id", "name", "website", "type"};

        Cursor cursor = db.query(
                "Sponsors",
                columns,
                null,
                null,
                null,
                null,
                null
        );

        // Verify if there are results
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Sponsor sponsor = new Sponsor();
                sponsor.setName(cursor.getString(1));
                sponsor.setWebsite(cursor.getString(2));
                sponsor.setType(cursor.getString(3));

                list.add(sponsor);
            } while (cursor.moveToNext());

        }

        cursor.close();
        return(list);
    }

    public void insertLecture(Lecture lecture) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.Lectures.COLUMN_NAME, lecture.getName());
        values.put(DBContract.Lectures.COLUMN_DESCRIPTION, lecture.getDescription());
        values.put(DBContract.Lectures.COLUMN_PERIOD, lecture.getPeriod());
        values.put(DBContract.Lectures.COLUMN_TIME, lecture.getTime());
        values.put(DBContract.Lectures.COLUMN_ROOM, lecture.getRoom());
        values.put(DBContract.Lectures.COLUMN_SPEAKER_ID, lecture.getSpeakerId());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBContract.Lectures.TABLE_NAME, null, values);
    }

    public List<Lecture> getLectures() {
        List<Lecture> list = new ArrayList<>();
        String[] columns = new String[]{"_id", "speakerId", "name", "description", "period", "time", "room", "speakerId"};

        Cursor cursor = db.query(
                "Lectures",
                columns,
                null,
                null,
                null,
                null,
                "time ASC"
        );

        // Verify if there are results
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Lecture lecture = new Lecture();
                lecture.setSpeakerId(cursor.getInt(1));
                lecture.setName(cursor.getString(2));
                lecture.setDescription(cursor.getString(3));
                lecture.setPeriod(cursor.getString(4));
                lecture.setTime(cursor.getInt(5));
                lecture.setRoom(cursor.getString(6));
                lecture.setSpeakerId(cursor.getInt(7));

                list.add(lecture);
            } while (cursor.moveToNext());

        }

        cursor.close();
        return(list);
    }
}
