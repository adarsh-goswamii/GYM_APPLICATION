package com.example.gym_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity implements PlanDetailsDialog.PassPlanInterface {

    @Override
    public void getPlan(Plan plan) {
        if(Utils.addPlan(plan))
        {
            Intent intent= new Intent(this, PlanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private TextView longDesc;
    private ImageView imageView;
    private Button add;
    static String TRAINING_KEY= "109";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training2);

        initViews();
        Intent intent= getIntent();
        if(intent!=null)
        {
            final Training training= intent.getParcelableExtra(TRAINING_KEY);
            if(training!= null)
            {
                longDesc.setText(training.getLongDesc());
                Glide.with(this).asBitmap().load(training.getImageUrl()).into(imageView);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PlanDetailsDialog dialog= new PlanDetailsDialog();
                        Bundle bundle= new Bundle();
                        bundle.putParcelable(TRAINING_KEY, training);
                        dialog.setArguments(bundle);

                        dialog.show(getSupportFragmentManager(), "plan details dialog");
                    }
                });
            }
        }
    }

    public void initViews()
    {
        longDesc= findViewById(R.id.long_description);
        imageView= findViewById(R.id.image_training);
        add= findViewById(R.id.add_to_plan_btn);
    }
}