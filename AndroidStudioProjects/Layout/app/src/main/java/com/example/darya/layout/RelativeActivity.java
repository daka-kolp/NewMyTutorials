package com.example.darya.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class RelativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);
    }

    public void onGridClicked(View view) {
        funcIntent(GridActivity.class);
    }

    public void funcIntent(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void onTBClicked(View view) {
        boolean on = ((ToggleButton)view).isChecked();

        if (on) {
           //
        } else {
            //
        }

    }
}
