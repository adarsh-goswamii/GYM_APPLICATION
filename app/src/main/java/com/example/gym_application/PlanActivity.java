package com.example.gym_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlanActivity extends AppCompatActivity {

    private RelativeLayout mondayRelative, sundayRelative, tuesdayRelative, wednesdayRelative, thursdayRelative, fridayRelative, saturdayRelative;
    private RecyclerView mondayRecycler, tuesdayRecycler, wednesdayRecycler, thursdayRecycler, fridayRecycler, saturdayRecycler, sundayRecycler ;
    private ImageView downMonday, upMonday, downTuesday, upTuesday, downWednesday, upWednesday, downThursday, upThursday, downFriday, upFriday, downSaturday, upSaturday;
    private ImageView downSunday, upSunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initView();

        onClickListeners();

    }

    private void onClickListeners()
    {
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

        downTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downTuesday.setVisibility(View.GONE);
                upTuesday.setVisibility(View.VISIBLE);
                tuesdayRecycler.setVisibility(View.VISIBLE);
            }
        });

        upTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upTuesday.setVisibility(View.GONE);
                downTuesday.setVisibility(View.VISIBLE);
                tuesdayRecycler.setVisibility(View.GONE);
            }
        });

        upWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upWednesday.setVisibility(View.GONE);
                downWednesday.setVisibility(View.VISIBLE);
                wednesdayRecycler.setVisibility(View.GONE);
            }
        });

        downWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downWednesday.setVisibility(View.GONE);
                upWednesday.setVisibility(View.VISIBLE);
                wednesdayRecycler.setVisibility(View.VISIBLE);
            }
        });

        downThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downThursday.setVisibility(View.GONE);
                upThursday.setVisibility(View.VISIBLE);
                thursdayRecycler.setVisibility(View.VISIBLE);
            }
        });

        upThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upThursday.setVisibility(View.GONE);
                downThursday.setVisibility(View.VISIBLE);
                thursdayRecycler.setVisibility(View.GONE);
            }
        });

        upFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upFriday.setVisibility(View.GONE);
                downFriday.setVisibility(View.VISIBLE);
                fridayRecycler.setVisibility(View.GONE);
            }
        });

        downFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downFriday.setVisibility(View.GONE);
                upFriday.setVisibility(View.VISIBLE);
                fridayRecycler.setVisibility(View.VISIBLE);
            }
        });

        downSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downSaturday.setVisibility(View.GONE);
                upSaturday.setVisibility(View.VISIBLE);
                saturdayRecycler.setVisibility(View.VISIBLE);
            }
        });

        upSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upSaturday.setVisibility(View.GONE);
                downSaturday.setVisibility(View.VISIBLE);
                saturdayRecycler.setVisibility(View.GONE);
            }
        });

        upSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upSunday.setVisibility(View.GONE);
                downSunday.setVisibility(View.VISIBLE);
                sundayRecycler.setVisibility(View.GONE);
            }
        });

        downSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downSunday.setVisibility(View.GONE);
                upSunday.setVisibility(View.VISIBLE);
                sundayRecycler.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView()
    {
        mondayRecycler= findViewById(R.id.mondayRecyler);
        mondayRelative= findViewById(R.id.mondayRelLayout);
        upMonday= findViewById(R.id.upArrow);
        downMonday= findViewById(R.id.downArrow);

        tuesdayRecycler= findViewById(R.id.mondayRecyler);
        tuesdayRelative= findViewById(R.id.mondayRelLayout);
        upTuesday= findViewById(R.id.tuesday_up);
        downTuesday= findViewById(R.id.tuesday_down);

        wednesdayRecycler= findViewById(R.id.mondayRecyler);
        wednesdayRelative= findViewById(R.id.mondayRelLayout);
        upWednesday= findViewById(R.id.wednesday_up);
        downWednesday= findViewById(R.id.wednesday_down);

        thursdayRecycler= findViewById(R.id.mondayRecyler);
        thursdayRelative= findViewById(R.id.mondayRelLayout);
        upThursday= findViewById(R.id.thursday_up);
        downThursday= findViewById(R.id.thursday_down);

        fridayRecycler= findViewById(R.id.mondayRecyler);
        fridayRelative= findViewById(R.id.mondayRelLayout);
        upFriday= findViewById(R.id.friday_up);
        downFriday= findViewById(R.id.friday_down);

        saturdayRecycler= findViewById(R.id.mondayRecyler);
        saturdayRelative= findViewById(R.id.mondayRelLayout);
        upSaturday= findViewById(R.id.saturdayup);
        downSaturday= findViewById(R.id.saturdaydown);

        sundayRecycler= findViewById(R.id.mondayRecyler);
        sundayRelative= findViewById(R.id.mondayRelLayout);
        upSunday= findViewById(R.id.sunday_up);
        downSunday= findViewById(R.id.sunday_down);
    }
}