package com.example.gym_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MusicPlayer extends AppCompatActivity {

    public static final int REQUEST_CODE= 100;
    public static final int SETTING_CODE= 108;
    private LinearLayout parent;
    public static  ArrayList<MusicFiles> musicFiles;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        parent= findViewById(R.id.parent);
        handlePermissions();
    }

    public void handlePermissions()
    {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            musicFiles= getAllAudio(this);
            initViewPager();
        }
        else
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                showSnackBar();
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }


    public void showSnackBar()
    {
        Snackbar.make(parent, "Storage permissions are required to fetch the songs from your device", Snackbar.LENGTH_INDEFINITE)
                .setAction("Grant Permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:"+getPackageName()));
                        startActivity(intent);
                    }
                }).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    musicFiles= getAllAudio(this);
                    initViewPager();
                }
                else
                    Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    void initViewPager()
    {
        viewPager= findViewById(R.id.viewPager);
        tabLayout= findViewById(R.id.tab_layout);

        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragments(new SongsFragment(), "Songs");
        adapter.addFragments(new AlbumFragment(), "Album");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);

            fragments= new ArrayList<>();
            titles= new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        void addFragments(Fragment fragment, String title)
        {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    public ArrayList<MusicFiles> getAllAudio(Context context)
    {
        ArrayList<MusicFiles> tempAudioList= new ArrayList<>();

        Uri uri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection= { MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,

        };

        Cursor cursor= context.getContentResolver().query(uri, projection, null, null, null);
        if(cursor!= null)
        {
            while(cursor.moveToNext()) {
                String album = cursor.getString(0);
                String artist= cursor.getString(1);
                String duration = cursor.getString(2);
                String title = cursor.getString(3);
                String path = cursor.getString(4);

                MusicFiles musicFiles = new MusicFiles(path, title, artist, duration, album);
                tempAudioList.add(musicFiles);
            }
            cursor.close();
        }
        return tempAudioList;
    }
}