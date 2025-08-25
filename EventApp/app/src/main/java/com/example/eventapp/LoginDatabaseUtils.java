// Russ Savela
// russell.savela@snhu.edu
// August 14, 2025
// CS 360 Event APP
//

// LoginDatabaseUtils.java
//  - This implements the database shell for SQLite
//      for the login database.  The Google documentation
//      suggest using Rooms instead to do this, but as the
//      instructions for the project didn't mention rooms,
//      SQLite is used directly.
//

package com.example.eventapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginDatabaseUtils extends SQLiteOpenHelper {
    private static final String LOGIN_DATABASE_DDL = """
                CREATE TABLE login_table(
                user_name TEXT PRIMARY KEY,
                password TEXT
                );
                """;

    public static final int DATABASE_VERSION = 1;
    // Constructor
    public LoginDatabaseUtils(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Required by Android
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the table
        db.execSQL(LOGIN_DATABASE_DDL);

    }

    public Boolean checkUserCredentials(String userName, String password) {

        SQLiteDatabase loginDB = this.getReadableDatabase();
        Cursor loginCur = loginDB.rawQuery("""
                
                        SELECT password
                FROM login_table
                WHERE user = ?
                """,
                new String[] {userName}
                );

        // Compare the password
        // assume the username is only there once

        String dbUserPassword = loginCur.getString(loginCur.getColumnIndexOrThrow("password"));
        loginCur.close();

        if (dbUserPassword == password) {
            // password was correct
            return true;
        }
        else {
            return false;
        }
    }

    public long addUser(String username, String password) {

        SQLiteDatabase loginDB = this.getWritableDatabase();
        long rowID;
        ContentValues insertValues = new ContentValues();

        // populate the data
        insertValues.put("user_name", username);
        insertValues.put("password", password);

        rowID = loginDB.insert("login_table", null, insertValues);

        return (rowID);
    }

    // Required by Android
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table and recreate
        //   it with the onCreate method.

        db.execSQL("DROP TABLE IF EXISTS login_table;");
        onCreate(db);
    }
}
