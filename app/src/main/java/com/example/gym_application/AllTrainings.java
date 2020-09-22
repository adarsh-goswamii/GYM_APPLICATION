package com.example.gym_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllTrainings extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TrainingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trainings);

        recyclerView= findViewById(R.id.recycler);
        adapter= new TrainingAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ArrayList<Training> trainings=Utils.getTrainings();
        if(trainings!=null)
            adapter.setList(trainings);
    }
}