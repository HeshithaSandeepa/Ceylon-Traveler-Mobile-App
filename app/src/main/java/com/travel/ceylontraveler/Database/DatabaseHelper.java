package com.travel.ceylontraveler.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.travel.ceylontraveler.MainActivity;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_IS_LOGGED_IN = "true";
    private static final String DATABASE_NAME = "ceylontraveler.db";
    public static final String TABLE_NAME = "loginDetailsTable";
    public static final String COLUMN_1 = "id";
    public static final String COLUMN_2 = "email";
    public static final String COLUMN_3 = "username";
    public static final String COLUMN_4 = "password";

    private static final int DATABASE_VERSION = 1;
    private Context context;

    //make table for store favorite items
    public static String TABLE_NAME2 = "favoriteTable";
    public static final String COLUMN_PAGE_NAME = "page_name";




    public DatabaseHelper(Context context) {  //initialize database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ////////////////////////////// create table query////////////////////
        String createTableQuery = "CREATE TABLE " + TABLE_NAME
                + " (" + COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_2 + " TEXT, "
                + COLUMN_3 + " TEXT, "
                + COLUMN_4 + " TEXT)";
        db.execSQL(createTableQuery);

        // Create the favorites table
        String createTableQuery2 = "CREATE TABLE " + TABLE_NAME2 + " ("
                + COLUMN_PAGE_NAME + " TEXT PRIMARY KEY)";
        db.execSQL(createTableQuery2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //  insert login details into the database//
    public boolean insertLoginDetails(String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1, (byte[]) null);
        contentValues.put(COLUMN_2, email);
        contentValues.put(COLUMN_3, username);
        contentValues.put(COLUMN_4, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }


    /////Check if a user exists in the database///
    public boolean isUserExist(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_1, COLUMN_3, COLUMN_4}; // Include COLUMN_1 for the user ID
        String selection = COLUMN_3 + " = ?" + " AND " + COLUMN_4 + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        boolean userExists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return userExists;
    }


    // delete account in database
    public void deleteAccount(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_1 + " = ?", new String[]{String.valueOf(id)});
        db.close();

        if (result > 0) {
            // Clear the saved preferences
            SharedPreferences preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            preferences.edit().clear().apply();

            // Show a success message delete account
            Toast.makeText(context, "Account deleted successfully", Toast.LENGTH_SHORT).show();

            // Restart the app or navigate to a desired screen
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Failed to delete account", Toast.LENGTH_SHORT).show();
        }
    }


                ///Insert a favorite page into the database///
    public void insertFavoritePage(String pageName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PAGE_NAME, pageName);
        db.insert(TABLE_NAME2, null, values);
        db.close();
    }
}

