package com.example.darya.coffeeshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        Coffee cof = Coffee.coffee[drinkNo];

        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(cof.getImageResourceId());
        photo.setContentDescription(cof.getName());

        TextView name = (TextView) findViewById(R.id.name);

        name.setText(cof.getName());

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(cof.getDescription());
    }

}