// Russ Savela
// russell.savela@snhu.edu
// August 14, 2025
// CS 360 Event APP
//

// EventDatabaseUtils.java
//  - This implements the database for SQLite
//      for the events database.
//

package com.example.eventapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class EventDatabaseUtils extends SQLiteOpenHelper {
    private static final String EVENT_DATABASE_DDL = """
                CREATE TABLE event_table(
                event_id INTEGER PRIMARY KEY AUTOINCREMENT,
                event_name TEXT,
                event_date TEXT,
                event_time TEXT
                );
                """;

    public static final int DATABASE_VERSION = 1;
    private Context context;
    // Constructor
    public EventDatabaseUtils(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    // Required by Android
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the event table
        db.execSQL(EVENT_DATABASE_DDL);

    }

    // get all events
    Cursor getAllEvents() {

        String eventQueryString = """
                SELECT * FROM event_table;
                """;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor eventReadAllCursor = null;

        if (db != null) {
            eventReadAllCursor = db.rawQuery(eventQueryString, null);
        }

        return (eventReadAllCursor);
    }

    public long addEvent(String eventName, String eventDate, String eventTime) {

        SQLiteDatabase eventDB = this.getWritableDatabase();
        long rowID;
        ContentValues insertValues = new ContentValues();

        // populate the data
        insertValues.put("event_name", eventName);
        insertValues.put("event_date", eventDate);
        insertValues.put("event_time", eventTime);

        rowID = eventDB.insert("event_table", null, insertValues);

        // -1 Row ID for failure
        if (rowID == -1) {
            Toast.makeText(context, "Error: Could not add event", Toast.LENGTH_SHORT).show();
        }

        return (rowID);
    }

    // delete an event
    long deleteEvent(String rowID) {

        SQLiteDatabase db = this.getWritableDatabase();

        long returnVal = db.delete("event_table",
                "event_id=?",
                new String[]{rowID});

        if (returnVal == -1) {

            Toast.makeText(context, "Error: Could not delete event", Toast.LENGTH_SHORT).show();

        }

        return (returnVal);
    }

    // update and event
    long updateEvent(String rowID, String eventName, String eventDate, String eventTime) {
        // get database connection
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put("event_name", eventName);
        updateValues.put("event_data", eventDate);
        updateValues.put("event_time", eventTime);

        long returnVal = db.update("event_table",
                updateValues,
                "event_id=?",
                new String[]{rowID} );

        if (returnVal == -1) {
            Toast.makeText(context, "Error: could not update event", Toast.LENGTH_SHORT).show();
        }

        return (returnVal);

    }

    // Required by Android
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table and recreate
        //   it with the onCreate method.

        db.execSQL("DROP TABLE IF EXISTS event_table;");
        onCreate(db);
    }
}
