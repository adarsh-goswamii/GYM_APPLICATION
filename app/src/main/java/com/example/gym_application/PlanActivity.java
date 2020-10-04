package com.example.gym_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlanActivity extends AppCompatActivity {

    private RelativeLayout mondayRelative;
    private RecyclerView mondayRecycler;
    private ImageView downMonday, upMonday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initView();

        downMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downMonday.setVisibility(View.GONE);
                upMonday.setVisibility(View.VISIBLE);
                mondayRecycler.setVisibility(View.VISIBLE);
            }
        });

        upMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upMonday.setVisibility(View.GONE);
                downMonday.setVisibility(View.VISIBLE);
                mondayRecycler.setVisibility(View.GONE);
            }
        });
    }

    private void initView()
    {
        mondayRecycler= findViewById(R.id.mondayRecyler);
        mondayRelative= findViewById(R.id.mondayRelLayout);
        upMonday= findViewById(R.id.upArrow);
        downMonday= findViewById(R.id.downArrow);
    }
}