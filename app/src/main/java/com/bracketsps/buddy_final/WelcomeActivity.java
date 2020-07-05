package com.bracketsps.buddy_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        new Timer().schedule(new TimerTask(){
            public void run() {
                WelcomeActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    }
                });
            }
        }, 4000);
    }

}
