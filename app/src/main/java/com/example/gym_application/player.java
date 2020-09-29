package com.example.gym_application;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.gym_application.MusicPlayer.musicFiles;

public class player extends AppCompatActivity
{
    private TextView artist_name, song_name, duration_played, duration_total;
    private ImageView cover_art, nextBtn, prevBtn, backBtn, shuffleBtn, repeatBtn;
    private FloatingActionButton playPauseBtn;
    private SeekBar seekBar;
    static ArrayList<MusicFiles> listSongs= new ArrayList<>();
    static Uri uri;
    static MediaPlayer mediaPlayer;
    private Handler handler= new Handler();
    static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initViews();
        getIntentMethod();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, int i, boolean b) {
                if(mediaPlayer!=null && b)
                    mediaPlayer.seekTo(i * 1000);

                player.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mediaPlayer!= null)
                        {
                            int  currentPosition= mediaPlayer.getCurrentPosition()/1000;
                            seekBar.setProgress(currentPosition);
                            duration_played.setText(formattedTime(currentPosition));
                        }
                        handler.postDelayed(this , 1000);
                    }
                });
            }

            private String formattedTime(int s)
            {
                String totalOut="";
                String second= String.valueOf(s% 60);
                String minute= String.valueOf(s/60);
                String totalNew="";

                totalOut= minute+":"+second;
                if(second.length()==1)
                    return totalNew;
                else
                    return totalOut;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void initViews()
    {
        artist_name= findViewById(R.id.artist_name);
        song_name= findViewById(R.id.song_name);
        duration_played= findViewById(R.id.duration_played);
        duration_total= findViewById(R.id.duration_total);
        cover_art= findViewById(R.id.cover_art);
        nextBtn= findViewById(R.id.next_btn);
        prevBtn= findViewById(R.id.previous_btn);
        backBtn= findViewById(R.id.back_btn);
        shuffleBtn= findViewById(R.id.shuffle_btn);
        repeatBtn= findViewById(R.id.repeat_btn);
        playPauseBtn= findViewById(R.id.play_pause);
        seekBar= findViewById(R.id.seekbar);
    }

    public void getIntentMethod()
    {
        position= getIntent().getIntExtra("position", -1);
        listSongs= musicFiles;
        if(listSongs!=null)
        {
            playPauseBtn.setImageResource(R.drawable.ic_pause);
            uri= Uri.parse(listSongs.get(position).getPath());
        }
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer= MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        else
        {
            mediaPlayer= MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        seekBar.setMax(mediaPlayer.getDuration()/1000);
    }
}