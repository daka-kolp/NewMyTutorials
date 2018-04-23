package com.example.darya.coffeeshop;

import android.content.Intent;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TopLevelActivity extends Activity {

    private SQLiteDatabase db;
    private Cursor favoriteCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        getListListener();
    }

    public void getListListener() {

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                if(position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);

        ListView listFavorites = (ListView) findViewById(R.id.list_favorites);
        try {
            favoriteCursor = createCursor(db);
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(
                    TopLevelActivity.this,
                    android.R.layout.simple_list_item_1,
                    favoriteCursor,
                    new String[] {CoffeeshopDatabaseHelper.NAME},
                    new int[] {android.R.id.text1},
                    0);
            listFavorites.setAdapter(favoriteAdapter);
        } catch (SQLiteException e) {
            messageToast();
        }

        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKNO, (int) id);
                startActivity(intent);
            }
        });
    }

    private Cursor createCursor(SQLiteDatabase database) {
        SQLiteOpenHelper coffeshopDatabaseHelper = new CoffeeshopDatabaseHelper(this);
        database = coffeshopDatabaseHelper.getReadableDatabase();
        Cursor cursor = database.query(
                CoffeeshopDatabaseHelper.DRINK,
                new String[] {"_id", CoffeeshopDatabaseHelper.NAME},
                CoffeeshopDatabaseHelper.FAVORITE + " = 1",
                null,
                null,
                null,
                null);
        return cursor;
    }

    private void messageToast() {
        Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            Cursor newCursor = createCursor(db);
            ListView listFavorites = (ListView) findViewById(R.id.list_favorites);
            CursorAdapter adapter = (CursorAdapter) listFavorites.getAdapter();
            adapter.changeCursor(newCursor);
        } catch (SQLiteException e) {
            messageToast();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoriteCursor.close();
        db.close();
    }
}
