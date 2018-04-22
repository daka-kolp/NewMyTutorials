package com.example.darya.coffeeshop;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends ListActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        try {
            SQLiteOpenHelper coffeeshopDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = coffeeshopDatabaseHelper.getReadableDatabase();
            cursor = db.query(
                    StarbuzzDatabaseHelper.DRINK,
                    new String[] {"_id", StarbuzzDatabaseHelper.NAME},
                    null,
                    null,
                    null,
                    null,
                    null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {StarbuzzDatabaseHelper.NAME},
                    new int[] {android.R.id.text1},
                    0);
            listView.setAdapter(listAdapter);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    public void getListWithAdapter () {
        ListView listView = getListView();
        ArrayAdapter<Coffee> listAdapter = new ArrayAdapter<Coffee>(this, android.R.layout.simple_list_item_1, Coffee.coffee);
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINKNO, (int) id);
        startActivity(intent);
    }
}
