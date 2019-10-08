package com.anamaniac.companyassistant.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.anamaniac.companyassistant.R;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent login = new Intent(splash.this, LoginActivty.class);
        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(login);
                    finish();
                }
            }
        };
        timer.start();

    }
}
