package com.example.darya.coffeeshop;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        showInfoCoffee();
    }

    public void showInfoCoffee() {
        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINKNO);
        try {
            SQLiteOpenHelper coffeshopDatabaseHelper = new CoffeeshopDatabaseHelper(this);
            SQLiteDatabase db = coffeshopDatabaseHelper.getWritableDatabase();
            Cursor cursor = db.query(
                    CoffeeshopDatabaseHelper.DRINK,
                    new String[] {
                            CoffeeshopDatabaseHelper.NAME,
                            CoffeeshopDatabaseHelper.DESCRIPTION,
                            CoffeeshopDatabaseHelper.IMAGE,
                            CoffeeshopDatabaseHelper.FAVORITE },
                    "_id = ?",
                    new String[] {Integer.toString(drinkNo)},
                    null,
                    null,
                    null);

            if (cursor.moveToFirst()) {
                //get data about drinks
                String nameText = cursor.getString(0);
                String descripionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1);

                //fill data
                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descripionText);

                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
            db.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
//        Coffee cof = Coffee.coffee[drinkNo];
//
//        ImageView photo = (ImageView) findViewById(R.id.photo);
//        photo.setImageResource(cof.getImageResourceId());
//        photo.setContentDescription(cof.getName());
//
//        TextView name = (TextView) findViewById(R.id.name);
//
//        name.setText(cof.getName());
//
//        TextView description = (TextView) findViewById(R.id.description);
//        description.setText(cof.getDescription());
    }

    public void onFavoriteClicked(View view) {
        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINKNO);

        new UpdateDrinkTask().execute(drinkNo);
//        CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
//        ContentValues drinkValues = new ContentValues();
//        drinkValues.put(CoffeeshopDatabaseHelper.FAVORITE, favorite.isChecked());
//
//        SQLiteOpenHelper coffeeshopDatabaseHelper = new CoffeeshopDatabaseHelper(DrinkActivity.this);
//        try {
//            SQLiteDatabase db = coffeeshopDatabaseHelper.getWritableDatabase();
//            db.update(
//                    CoffeeshopDatabaseHelper.DRINK,
//                    drinkValues,
//                    "_id=?",
//                    new String[]{Integer.toString(drinkNo)});
//            db.close();
//        } catch (SQLiteException e) {
//
//            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
//            toast.show();
//        }
    }

    private class UpdateDrinkTask extends AsyncTask <Integer, Void, Boolean> {

        ContentValues drinkValues;

        @Override
        protected void onPreExecute() {
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
            drinkValues = new ContentValues();
            drinkValues.put(CoffeeshopDatabaseHelper.FAVORITE, favorite.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... drinks) {
            int drinkNo = drinks[0];
            SQLiteOpenHelper coffeeshopDatabaseHelper = new CoffeeshopDatabaseHelper(DrinkActivity.this);
            try {
                SQLiteDatabase db = coffeeshopDatabaseHelper.getWritableDatabase();
                db.update(
                        CoffeeshopDatabaseHelper.DRINK,
                        drinkValues,
                        "_id=?",
                        new String[]{Integer.toString(drinkNo)});
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }



        @Override
        protected void onPostExecute(Boolean o) {
            if(!o) {
                Toast toast = Toast.makeText(DrinkActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}