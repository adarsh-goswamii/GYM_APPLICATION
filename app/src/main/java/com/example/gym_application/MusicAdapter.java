package com.example.gym_application;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>
{
    public static ArrayList<MusicFiles> mfiles;
    public Context mcontext;

    public MusicAdapter(ArrayList<MusicFiles> mfiles, Context context)
    {
        this.mcontext= context;
        this.mfiles= mfiles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.music_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mfiles.get(position).getTitle());
        byte[] image=getAlbumArt(mfiles.get(position).getPath());
        if(image!=null)
        {
            Glide.with(mcontext).asBitmap().load(image).into(holder.image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mcontext, player.class);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.musicName);
            image= itemView.findViewById(R.id.musicImage);
        }
    }

    private byte[] getAlbumArt(String uri)
    {
        MediaMetadataRetriever retriever= new MediaMetadataRetriever();
        retriever.setDataSource(uri);

        byte[] art= retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
