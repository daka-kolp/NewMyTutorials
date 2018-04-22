package com.example.darya.coffeeshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "coffeshop";
    private static final int DB_VERSION = 2;

    public static final String DRINK = "DRINK";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String IMAGE = "IMAGE_RESOURCE_ID";

    public StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put(NAME, name);
        drinkValues.put(DESCRIPTION, description);
        drinkValues.put(IMAGE, resourceId);
        db.insert(DRINK, null, drinkValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int currentVersion, int newVersion) {
        updateMyDatabase(db, currentVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int currentVersion, int oldVersion) {

    }

    private void updateMyDatabase(SQLiteDatabase db, int currentVersion, int newVersion) {
        if (currentVersion < 1) {
            db.execSQL("CREATE TABLE DRINK ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");

            insertDrink(db, "Latte", "Espresso with milk", R.drawable.ic_action_latte);
            insertDrink(db, "Dark", "Espresso, milk, foam", R.drawable.ic_action_dark);
            insertDrink(db, "Cappuccino", "Espresso, chocolate, milk, foam", R.drawable.ic_action_cappuchino);
        }
        if (currentVersion < 2) {
          db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
