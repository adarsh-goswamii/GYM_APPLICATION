package com.example.gym_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder>
{
    private ArrayList<Training> list;
    private Context context;

    public TrainingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_training, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.shortDesc.setText(list.get(position).getShortDesc());

        Glide.with(context).asBitmap().load(list.get(position).getImageUrl()).into(holder.imageView);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: intent.
            }
        });
    }

    public void setList(ArrayList<Training> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private MaterialCardView parent;
        private TextView name, shortDesc;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent= itemView.findViewById(R.id.parent);
            name= itemView.findViewById(R.id.name);
            shortDesc= itemView.findViewById(R.id.shortDesc);
            imageView= itemView.findViewById(R.id.imageView);
        }
    }
}
