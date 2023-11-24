package com.uta.maptesting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Generate a random time between 3500 and 6500 milliseconds
        int minTime = 3000;
        int maxTime = 6000;
        int randomTime = new Random().nextInt(maxTime - minTime + 1) + minTime;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start main activity after the random time
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, randomTime); // Use the random time here
    }
}
