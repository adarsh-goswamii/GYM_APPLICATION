package com.example.gym_application;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    private Thread playThread, prevThread, nextThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initViews();
        getIntentMethod();
        song_name.setText(listSongs.get(position).getTitle());
        artist_name.setText(listSongs.get(position).getArtist());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, int i, boolean b) {
                if(mediaPlayer!=null && b)
                    mediaPlayer.seekTo(i * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
        totalNew= minute+":"+"0"+second;
        if(second.length()==1)
            return totalNew;
        else
            return totalOut;
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
        metaData(uri);
    }

    private void metaData(Uri uri)
    {
        MediaMetadataRetriever retriever= new MediaMetadataRetriever();
        retriever.setDataSource(uri.toString());
        int durationTotal= Integer.parseInt(listSongs.get(position).getDuration())/1000;
        duration_total.setText(formattedTime(durationTotal));
        byte[] art= retriever.getEmbeddedPicture();
        if(art!=null)
        {
            Glide.with(this).load(art).into(cover_art);
        }
        else
            Glide.with(this).load(R.mipmap.ic_logo_foreground).into(cover_art);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playThreadBtn();
        prevThreadBtn();
        nextThreadBtn();
    }

    private void playThreadBtn() {
        playThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                playPauseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        playPauseBtnClicked();
                    }
                });
            }
        };
        playThread.start();
    }

    private void playPauseBtnClicked()
    {
        if(mediaPlayer.isPlaying())
        {
            playPauseBtn.setImageResource(R.drawable.ic_play);
            mediaPlayer.pause();
            seekBar.setMax(mediaPlayer.getDuration()/1000);
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
        else
        {
            playPauseBtn.setImageResource(R.drawable.ic_pause);
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration()/1000);
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
    }

    private void prevThreadBtn()
    {
        prevThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                prevBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prevBtnClicked();
                    }
                });
            }
        };
        prevThread.start();
    }

    private void prevBtnClicked()
    {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            position= ((position+ listSongs.size())- 1)% listSongs.size();
            uri= Uri.parse(listSongs.get(position).getPath());
            mediaPlayer= MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            song_name.setText(listSongs.get(position).getTitle());
            artist_name.setText(listSongs.get(position).getArtist());
            seekBar.setMax(mediaPlayer.getDuration()/1000);
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
            playPauseBtn.setImageResource(R.drawable.ic_pause);
            mediaPlayer.start();
        }
        else
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            position= (position+ 1)% listSongs.size();
            uri= Uri.parse(listSongs.get(position).getPath());
            mediaPlayer= MediaPlayer.create(getApplicationContext(), uri);
            song_name.setText(listSongs.get(position).getTitle());
            metaData(uri);
            artist_name.setText(listSongs.get(position).getArtist());
            seekBar.setMax(mediaPlayer.getDuration()/1000);
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
            playPauseBtn.setImageResource(R.drawable.ic_play);
        }
    }

    private void nextThreadBtn()
    {
        nextThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextBtnClicked();
                    }
                });
            }
        };
        nextThread.start();
    }

    private void nextBtnClicked()
    {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            position= (position+ 1)% listSongs.size();
            uri= Uri.parse(listSongs.get(position).getPath());
            mediaPlayer= MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            song_name.setText(listSongs.get(position).getTitle());
            artist_name.setText(listSongs.get(position).getArtist());
            seekBar.setMax(mediaPlayer.getDuration()/1000);
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
            playPauseBtn.setImageResource(R.drawable.ic_pause);
            mediaPlayer.start();
        }
        else
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            position= (position+ 1)% listSongs.size();
            uri= Uri.parse(listSongs.get(position).getPath());
            mediaPlayer= MediaPlayer.create(getApplicationContext(), uri);
            song_name.setText(listSongs.get(position).getTitle());
            metaData(uri);
            artist_name.setText(listSongs.get(position).getArtist());
            seekBar.setMax(mediaPlayer.getDuration()/1000);
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
            playPauseBtn.setImageResource(R.drawable.ic_play);
        }
    }
}