package com.travel.ceylontraveler.Methods;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.travel.ceylontraveler.Database.DatabaseHelper;
import com.travel.ceylontraveler.R;

public class AddRemoveFavoriteActivity {

    public static void setupFavoriteButton(final Context context, final View view, final String currentPageName) {
        final Button favoriteButton = view.findViewById(R.id.favorite_icon);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            boolean isPageAdded = false; //  track the state page added or not

            @Override
            public void onClick(View v) {
                // Insert or remove the page name from the database based on the flag
                DatabaseHelper dbHelper = new DatabaseHelper(context.getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if (!isPageAdded) {
                    // Insert the page name into the database
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.COLUMN_PAGE_NAME, currentPageName);
                    db.insert(DatabaseHelper.TABLE_NAME2, null, values);
                    isPageAdded = true;
                    Toast.makeText(context.getApplicationContext(), "Page added to Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    // Remove the page name from the database
                    String whereClause = DatabaseHelper.COLUMN_PAGE_NAME + " = ?";
                    String[] whereArgs = {currentPageName};
                    db.delete(DatabaseHelper.TABLE_NAME2, whereClause, whereArgs);
                    isPageAdded = false;
                    Toast.makeText(context.getApplicationContext(), "Page removed from Favorites", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
    }
}
