package com.example.gym_application;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

public class MusicFiles
{
    private static String path, title, artist, duration, album;

    public MusicFiles(String path, String title, String artist, String duration, String album) {
        this.path = path;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
    }

    public MusicFiles() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public static ArrayList<MusicFiles> getAllAudio(Context context)
    {
        ArrayList<MusicFiles> tempAudioList= new ArrayList<>();

        Uri uri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection= { MediaStore.Audio.Media.ALBUM,
                               MediaStore.Audio.Media.ARTIST,
                               MediaStore.Audio.Media.DURATION,
                               MediaStore.Audio.Media.DATA,
        };

        Cursor cursor= context.getContentResolver().query(uri, projection, null, null, null);
        if(cursor!= null)
        {
            while(cursor.moveToNext())
            {
                String album= cursor.getString(0);
                String title= cursor.getString(1);
                String duration= cursor.getString(2);
                String path= cursor.getString(3);
                String artist= cursor.getString(4);
            }

            MusicFiles musicFiles= new MusicFiles(path, title, artist, duration, album);
            tempAudioList.add(musicFiles);
            cursor.close();
        }
        return tempAudioList;
    }
}
