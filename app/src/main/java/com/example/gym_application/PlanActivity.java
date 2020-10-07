package com.example.gym_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.gym_application.Utils.getPlans;

public class PlanActivity extends AppCompatActivity {

    private RelativeLayout mondayRelative, sundayRelative, tuesdayRelative, wednesdayRelative, thursdayRelative, fridayRelative, saturdayRelative;
    private RecyclerView mondayRecycler, tuesdayRecycler, wednesdayRecycler, thursdayRecycler, fridayRecycler, saturdayRecycler, sundayRecycler ;
    private ImageView downMonday, upMonday, downTuesday, upTuesday, downWednesday, upWednesday, downThursday, upThursday, downFriday, upFriday, downSaturday, upSaturday;
    private ImageView downSunday, upSunday;
    private ArrayList<Plan> plans;
    private RelativeLayout noplans;
    private NestedScrollView parent;
    private Button btnAddPlan;
    private PlanAdapter mondayAdapter, tuesdayAdapter, wednesdayAdapter, thursdayAdapter, fridayAdapter, saturdayAdapter, sundayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initView();

        plans= getPlans();
        if(plans!=null)
        {
            if (plans.size() == 0)
            {
                noplans.setVisibility(View.VISIBLE);
                parent.setVisibility(View.GONE);
                btnAddPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(PlanActivity.this, "hello there", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PlanActivity.this, AllTrainings.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }
            else
            {
                parent.setVisibility(View.VISIBLE);
                noplans.setVisibility(View.GONE);

                initAdapter();
                onClickListeners();
            }
        }
        else
        {
            noplans.setVisibility(View.VISIBLE);
            parent.setVisibility(View.GONE);
            btnAddPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(PlanActivity.this, "hello there", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(PlanActivity.this, AllTrainings.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }

    }

    private void initAdapter()
    {
        mondayAdapter= new PlanAdapter(this);
        mondayRecycler.setAdapter(mondayAdapter);
        mondayRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mondayAdapter.setPlans(getPlansByDay("Monday"));

        tuesdayAdapter= new PlanAdapter(this);
        tuesdayRecycler.setAdapter(tuesdayAdapter);
        tuesdayRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        tuesdayAdapter.setPlans(getPlansByDay("Tuesday"));

        wednesdayAdapter= new PlanAdapter(this);
        wednesdayRecycler.setAdapter(wednesdayAdapter);
        wednesdayRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        wednesdayAdapter.setPlans(getPlansByDay("Wednesday"));

        thursdayAdapter= new PlanAdapter(this);
        thursdayRecycler.setAdapter(thursdayAdapter);
        thursdayRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        thursdayAdapter.setPlans(getPlansByDay("Thursday"));

        fridayAdapter= new PlanAdapter(this);
        fridayRecycler.setAdapter(fridayAdapter);
        fridayRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        fridayAdapter.setPlans(getPlansByDay("Friday"));

        saturdayAdapter= new PlanAdapter(this);
        saturdayRecycler.setAdapter(saturdayAdapter);
        saturdayRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        saturdayAdapter.setPlans(getPlansByDay("Saturday"));

        sundayAdapter= new PlanAdapter(this);
        sundayRecycler.setAdapter(sundayAdapter);
        sundayRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        sundayAdapter.setPlans(getPlansByDay("Sunday"));
    }

    private ArrayList<Plan> getPlansByDay(String day)
    {
        ArrayList<Plan> plans= Utils.getPlans();
        ArrayList<Plan> ret= new ArrayList<>();

        for(Plan i: plans)
        {
            if(i.getDay().equalsIgnoreCase(day))
                ret.add(i);
        }
        return ret;
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
        noplans= findViewById(R.id.ifNoPlans);
        parent= findViewById(R.id.parent);
        btnAddPlan= findViewById(R.id.navigate);

        mondayRecycler= findViewById(R.id.mondayRecyler);
        mondayRelative= findViewById(R.id.mondayRelLayout);
        upMonday= findViewById(R.id.upArrow);
        downMonday= findViewById(R.id.downArrow);

        tuesdayRecycler= findViewById(R.id.tuesdayRecyler);
        tuesdayRelative= findViewById(R.id.tuesdayRelLayout);
        upTuesday= findViewById(R.id.tuesday_up);
        downTuesday= findViewById(R.id.tuesday_down);

        wednesdayRecycler= findViewById(R.id.wednesdayRecyler);
        wednesdayRelative= findViewById(R.id.wednesdayRelLayout);
        upWednesday= findViewById(R.id.wednesday_up);
        downWednesday= findViewById(R.id.wednesday_down);

        thursdayRecycler= findViewById(R.id.thursdayRecyler);
        thursdayRelative= findViewById(R.id.thursdayRelLayout);
        upThursday= findViewById(R.id.thursday_up);
        downThursday= findViewById(R.id.thursday_down);

        fridayRecycler= findViewById(R.id.fridayRecyler);
        fridayRelative= findViewById(R.id.fridayRelLayout);
        upFriday= findViewById(R.id.friday_up);
        downFriday= findViewById(R.id.friday_down);

        saturdayRecycler= findViewById(R.id.saturdayRecyler);
        saturdayRelative= findViewById(R.id.saturdayRelLayout);
        upSaturday= findViewById(R.id.saturdayup);
        downSaturday= findViewById(R.id.saturdaydown);

        sundayRecycler= findViewById(R.id.sundayRecyler);
        sundayRelative= findViewById(R.id.sundayRelLayout);
        upSunday= findViewById(R.id.sunday_up);
        downSunday= findViewById(R.id.sunday_down);
    }
}