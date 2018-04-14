package com.example.darya.workout;

import android.app.FragmentTransaction;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        WorkoutDetailFragment fragD = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
//        fragD.setWorkoutId(1);
    }

    @Override
    public void itemClicked(long id) {
        WorkoutDetailFragment details = new WorkoutDetailFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        details.setWorkoutId(id);
        ft.replace(R.id.fragment_container, details);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();

    }
}
