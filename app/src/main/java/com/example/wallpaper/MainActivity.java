package com.example.wallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button pickbtn,stopbtn;
    Timer myTimer;
    WallpaperManager wpm;
    int prev=1;
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myTimer = new Timer();
        wpm = WallpaperManager.getInstance(this);
        pickbtn = findViewById(R.id.button);
        stopbtn = findViewById(R.id.stopbtn);
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTimer.cancel();
            }
        });
        pickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper();
            }
        });
    }
    private void setWallpaper() {
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(prev==1){
                    drawable = getResources().getDrawable(R.drawable.one);
                    prev=2;
                }
                if(prev==2){
                    drawable = getResources().getDrawable(R.drawable.two);
                    prev=3;
                }
                if(prev==3) {
                    drawable = getResources().getDrawable(R.drawable.three);
                    prev = 1;
                }
                Bitmap wallper =((BitmapDrawable)drawable).getBitmap();
                try {
                    wpm.setBitmap(wallper);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 3000);
    }

    }
