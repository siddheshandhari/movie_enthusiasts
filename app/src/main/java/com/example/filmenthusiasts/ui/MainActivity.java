package com.example.filmenthusiasts.ui;

import android.os.Bundle;

import com.example.filmenthusiasts.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callMovieListFragment();
    }

    void callMovieListFragment() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.main_fragment, new MovieListFragment().newInstance())
                .commit();

    }
}