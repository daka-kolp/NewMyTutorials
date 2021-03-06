package com.example.darya.workout;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

public class WorkoutDetailFragment extends Fragment {

    private  long workoutId;

    public static final String WORKOUT_ID = "workoutId";
    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) workoutId = savedInstanceState.getLong(WORKOUT_ID);
        else {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        WatchFragment watchFragment = new WatchFragment();
        ft.replace(R.id.watch_container, watchFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null) {
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getNameEx());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(WORKOUT_ID, workoutId);
    }
}
