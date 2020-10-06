package com.example.gym_application;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.gym_application.TrainingActivity.TRAINING_KEY;
import static com.example.gym_application.Utils.getPlans;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Plan> plans;
    public PlanAdapter(Context context)
    {
        this.context= context;
        plans= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.trainingName.setText(plans.get(position).getTraining().getName());
        holder.setTime.setText(String.valueOf(plans.get(position).getMinutes()));
        holder.setDescription.setText(plans.get(position).getTraining().getShortDesc());
        Glide.with(context).asBitmap().load(plans.get(position).getTraining().getImageUrl()).into(holder.cardImage);

        if(plans.get(position).isAccomplished())
        {
            holder.checked.setVisibility(View.VISIBLE);
            holder.not_checked.setVisibility(View.GONE);
        }
        else
        {
            holder.not_checked.setVisibility(View.VISIBLE);
            holder.checked.setVisibility(View.GONE);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, AllTrainings.class);
                intent.putExtra(TRAINING_KEY, plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void setPlans(ArrayList<Plan> plans)
    {
        this.plans= plans;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView trainingName, setTime, setDescription;
        private ImageView cardImage, checked, not_checked;
        private RelativeLayout card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            trainingName= itemView.findViewById(R.id.card_name);
            setTime= itemView.findViewById(R.id.set_time);
            checked= itemView.findViewById(R.id.checked);
            not_checked= itemView.findViewById(R.id.not_checked);
            setDescription= itemView.findViewById(R.id.set_description);
            cardImage= itemView.findViewById(R.id.card_image);
            card= itemView.findViewById(R.id.card);
        }
    }
}
