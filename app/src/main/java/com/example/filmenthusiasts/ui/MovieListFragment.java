package com.example.filmenthusiasts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmenthusiasts.R;
import com.example.filmenthusiasts.adapter.AllMovieAdapter;
import com.example.filmenthusiasts.viewModel.MovieListViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieListFragment extends Fragment {

    RecyclerView recyclerView;
    AllMovieAdapter allMovieAdapter;
    MovieListViewModel movieListViewModel;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.main_activity_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        allMovieAdapter = new AllMovieAdapter(getActivity());
        recyclerView.setAdapter(allMovieAdapter);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        movieListViewModel.getMutableMoviesByRankModel().observe(getViewLifecycleOwner(), moviesByRankList -> {
            allMovieAdapter.updateList(moviesByRankList);
        });
    }
}