package com.example.darya.coffeeshop;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            SQLiteOpenHelper coffeshopDatabaseHelper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = coffeshopDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query(
                    StarbuzzDatabaseHelper.DRINK,
                    new String[] {StarbuzzDatabaseHelper.NAME, StarbuzzDatabaseHelper.DESCRIPTION, StarbuzzDatabaseHelper.IMAGE},
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

                //fill data
                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descripionText);

                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
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

}