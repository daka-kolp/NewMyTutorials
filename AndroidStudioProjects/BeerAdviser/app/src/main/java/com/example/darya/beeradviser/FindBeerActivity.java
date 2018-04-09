package com.example.darya.beeradviser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class FindBeerActivity extends Activity {
    BeefExpert beefExpert = new BeefExpert();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }
    public void onClickFindBeer(View view) {
        //получить ссылку на TextView
        TextView brands = (TextView) findViewById(R.id.brands);
        //получить ссылку на Spinner
        Spinner color = (Spinner) findViewById(R.id.color);
        //получить выбранный вариант в Spinner
        String beerType = String.valueOf(color.getSelectedItem());
        //вывод выбранного варианта

        List<String> listNameBeer = beefExpert.getBrands(beerType);
        StringBuilder namesBeer = new StringBuilder();
        for(String nameOfBeer : listNameBeer){
            namesBeer.append(nameOfBeer).append("\n");
        }
        brands.setText(namesBeer);
    }
}
